//복습 창 UI ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.UserWord;

@Repository
public class ReviewUIServerManager {
	
	private String id;
	private String grade;
	
	@Autowired
	private ConnectionManager connectionManager;
	
	/**
	 * Client에 학습할 단어 List를 보낸다.
	 * @param 사용자의 id
	 * @param 사용자의 grade
	 * @return 학습할 단어가 담겨있는 ArrayList
	 */
	public ArrayList<UserWord> getReviewWord(String id, String grade) {
		this.id = id;
		this.grade = grade;
		
		ArrayList<UserWord> result = new ArrayList<>();
		ArrayList<Integer[]> studiedIndexList = new ArrayList<>(); 
		ArrayList<Integer[]> studyIndexList = new ArrayList<>();
		
		//사용자가 기존에 학습한 단어의 index를 저장
		Connection con = connectionManager.getConnection();
		try {
			String sql = "SELECT S.WORDNO, S.SCOUNT FROM USERINFO U, STUDY S WHERE U.USERNO=S.USERNO AND U.USERID = ? AND S.GRADE = ? AND S.SCOUNT != 5";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, grade);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer[] temp = {rs.getInt("wordno"), rs.getInt("scount")};
				studiedIndexList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//random함수를 이용한 학습할 단어 wordNo 난수 발생
		Random r = new Random();
		int studySize = 20;
		int studiedIndexList_Size = studiedIndexList.size();
		if(studiedIndexList_Size < studySize) {
			studySize = studiedIndexList_Size;
		}
		
		while(studyIndexList.size() < studySize) {
			int temp = r.nextInt(studiedIndexList_Size);
			Integer[] tempSet = studiedIndexList.get(temp);
			if(!studyIndexList.contains(tempSet)) {
				studyIndexList.add(tempSet);
			}
		}
		
		//단어장에서 학습할 단어를 불러와서 ArrayList에 담기
		try {
			for(Integer[] i : studyIndexList) {
				String sql = null;
				if(grade.equals("N1")) sql = "select no, word, mean from n1 where no = ?";
				else if(grade.equals("N2")) sql = "select no, word, mean from n2 where no = ?";
				else if(grade.equals("N3")) sql = "select no, word, mean from n3 where no = ?";
				else if(grade.equals("N4")) sql = "select no, word, mean from n4 where no = ?";
				else if(grade.equals("N5")) sql = "select no, word, mean from n5 where no = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, i[0]);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				result.add(new UserWord(rs.getInt(1), rs.getString(2), rs.getString(3), i[1]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 사용자가 학습한 index를 study 테이블에 저장
	 * @param 사용자가 학습한 wordno가 담겨있는 reviewWordNo
	 */
	public void saveReviewWord(HashSet<Integer> reviewWordNo) {
		Connection con = connectionManager.getConnection();
		String sql = "update study set scount = scount+1 where wordno = ?";
		for(int i : reviewWordNo) {
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

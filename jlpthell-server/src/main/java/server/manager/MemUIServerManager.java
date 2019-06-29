//암기 창 UI ServerManager
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

import server.vo.UserWord;

@Repository
public class MemUIServerManager {
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
	public ArrayList<UserWord> getStudyWord(String id, String grade) {
		this.id = id;
		this.grade = grade;
		
		ArrayList<UserWord> result = new ArrayList<>(); 
		HashSet<Integer> studiedIndexList = new HashSet<>();
		HashSet<Integer> studyIndexList = new HashSet<>();
		int maxWordNum = 0;
		
		//사용자가 기존에 학습한 단어의 index를 저장
		Connection con = connectionManager.getConnection();
		try {
			String sql = "SELECT S.WORDNO FROM USERINFO U, STUDY S WHERE U.USERNO=S.USERNO AND U.USERID = ? AND S.GRADE = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, grade);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				studiedIndexList.add(rs.getInt("wordno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//등급에 따른 단어장 저장 갯수 확인 및 저장
		try {
			String sql = null;
			if(grade.equals("N1")) sql = "select count(*) from n1";
			else if(grade.equals("N2")) sql = "select count(*) from n2";
			else if(grade.equals("N3")) sql = "select count(*) from n3";
			else if(grade.equals("N4")) sql = "select count(*) from n4";
			else if(grade.equals("N5")) sql = "select count(*) from n5";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			maxWordNum = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//random함수를 이용한 학습할 단어 wordNo 난수 발생, 학습한 단어의 wordNo는 제외
		Random r = new Random();
		while(studyIndexList.size() < 20) {
			int temp = r.nextInt(maxWordNum) + 1;
			if(!studiedIndexList.contains(temp)) {
				studyIndexList.add(temp);
			}
		}
		
		//단어장에서 학습할 단어를 불러와서 ArrayList에 담기
		try {
			String sql = null;
			int count = 1;
			if(grade.equals("N1")) sql = "select no, word, mean from n1 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N2")) sql = "select no, word, mean from n2 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N3")) sql = "select no, word, mean from n3 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N4")) sql = "select no, word, mean from n4 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N5")) sql = "select no, word, mean from n5 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			for(int i : studyIndexList) {
				pstmt.setInt(count++, i);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(new UserWord(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 사용자가 학습한 index를 study 테이블에 저장
	 * @param 사용자가 학습한 wordno가 담겨있는 studyWordNo
	 */
	public void saveStudyWord(HashSet<Integer> studyWordNo) {
		Connection con = connectionManager.getConnection();
		try {
			String sql = "insert into study (userno, grade, wordno, scount) values ((select userno from userinfo where userid = ?), ?, ?, 0)";
			for(int i : studyWordNo) {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, grade);
				pstmt.setInt(3, i);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

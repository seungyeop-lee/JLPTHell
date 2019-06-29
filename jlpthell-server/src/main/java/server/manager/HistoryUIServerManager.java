//학습 이력 창 UI ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import server.vo.UserWord;

@Repository
public class HistoryUIServerManager {
	
	@Autowired
	private ConnectionManager connectionManager;
	
	//현재 사용자의 학습 이력 검색 메소드, 학습 이력을 UserWord객체에 담고, arraylist에 넣어서 반환
	public ArrayList<UserWord> userWordList(String id) {
		ArrayList<UserWord> result = new ArrayList<>();
		HashSet<Integer[]> wordNoList = new HashSet<>();
		Connection con = connectionManager.getConnection();
		String grade = null;
		
		//사용자가 학습한 이력 검색
		try {
			String sql = "select wordno, scount, grade "
					+ "from study "
					+ "where userno = (select userno from userinfo where userid = ?) and "
					+ "grade = (select grade from userinfo where userid = ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				wordNoList.add(new Integer[]{rs.getInt(1), rs.getInt(2)});
				grade = rs.getString(3);
			}
			while(rs.next()) {
				wordNoList.add(new Integer[]{rs.getInt(1), rs.getInt(2)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//학습한 이력이 없어서  grade에 초기값이 남아있을 경우 메소드 종료
		if(grade == null) {
			return result;
		}
		
		//학습한 단어의 wordno와 grade를 이용하여 실제 학습한 단어와 뜻을 검색
		try {
			String sql = "";
			if(grade.equals("N1")) sql = "select word, mean from n1 where no = ?";
			else if(grade.equals("N2")) sql = "select word, mean from n2 where no = ?";
			else if(grade.equals("N3")) sql = "select word, mean from n3 where no = ?";
			else if(grade.equals("N4")) sql = "select word, mean from n4 where no = ?";
			else if(grade.equals("N5")) sql = "select word, mean from n5 where no = ?";
			for(Integer[] i : wordNoList) {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, i[0]);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					result.add(new UserWord(rs.getString(1), rs.getString(2), i[1]));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}

//Minigame UI 창 ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import vo.UserWord;

public class MiniGameUIServerManager {
	/**
	 * 학습한 단어 중 미니게임에서 사용 할 단어 검색 및 반환
	 * @param 사용자의 id
	 * @param 사용자의 grade
	 * @return 미니게임에서 사용할 단어
	 */
	public ArrayList<UserWord> getGameWordList(String id, String grade) {
		ArrayList<UserWord> result = new ArrayList<>();
		ArrayList<Integer> studiedIndexList = new ArrayList<>();
		HashSet<Integer> gameIndexList = new HashSet<>();
		
		//사용자가 기존에 학습한 단어의 index를 저장
		Connection con = ConnectionManager.getConnection();
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
		
		//학습한 단어의 총 갯수가 10개 이상인지 확인
		if(studiedIndexList.size() < 10) {
			return result;
		}
		
		//random함수를 이용한 게임에 사용 할 wordNo 난수 발생
		Random r = new Random();
		while(gameIndexList.size() < 10) {
			int temp = r.nextInt(studiedIndexList.size());
			gameIndexList.add(studiedIndexList.get(temp));
		}
		
		//단어장에서 게임에 사용 할 단어를 불러와서 ArrayList에 담기
		try {
			String sql = null;
			int count = 1;
			if(grade.equals("N1")) sql = "select no, word, mean from n1 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N2")) sql = "select no, word, mean from n2 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N3")) sql = "select no, word, mean from n3 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N4")) sql = "select no, word, mean from n4 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			else if(grade.equals("N5")) sql = "select no, word, mean from n5 where no in(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			for(int i : gameIndexList) {
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
}
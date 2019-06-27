//Setting UI ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingUIServerManager {
	Connection con = ConnectionManager.getConnection(); 
	//급수 변경 메소드, 급수 변경 결과를  int로 반환
	//변경에 성공하면 1, 변경에 실패하면 -1, 현재와 같은 급수이면 0을 반환
	public int changeGrade(String id, String grade) {
		int result = -1;
		//매개변수로 사용자가 변경하고 싶은 급수를 받음
		try {
			String sql = "update userinfo set grade = ? where userid = ? and grade != ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, grade);
			pstmt.setString(2, id);
			pstmt.setString(3, grade);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//학습 이력 초기화 메소드, 학습 이력 초기화 결과를 boolean으로 반환
	public boolean initialize(String id, String grade) {
		boolean result = false;
		String sql = "delete study where userno = (select userno from userinfo where userid = ?)  and grade =?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, grade);
			if(pstmt.executeUpdate() != 0){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//아이디 삭제 메소드, 아이디 삭제 결과를 boolean으로 반환
	public boolean deleteUser(String id) {
		boolean result = false;
		try {
			String sql = "DELETE from USERINFO where userid = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			if(pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
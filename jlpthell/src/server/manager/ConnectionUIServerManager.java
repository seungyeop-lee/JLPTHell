//로그인 창 UI ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUIServerManager {
	
	//password hint 반환 메소드, password hint를 String으로 반환
	public String pwHint(String id) {
		Connection con = ConnectionManager.getConnection();
		String result = null;
		//사용자에게 받은 id로 db를 검색하여 password Hint를 찾음
		//없는 id라면 null값을 반환
		try {
			String sql = "select pwhint from userinfo where userid = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString("pwhint");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//login 메소드
	public String login(String[] loginInfo) {
		Connection con = ConnectionManager.getConnection();
		String result = null;
		//loginInfo에 들어있는 id와 pw를 db에서 확인하여 login 유무 확인
		//loginInfo[0] == String id, loginInfo[1] == String pw
		String sql = "select grade from userinfo where userid = ? and userpw = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginInfo[0]);
			pstmt.setString(2, loginInfo[1]);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

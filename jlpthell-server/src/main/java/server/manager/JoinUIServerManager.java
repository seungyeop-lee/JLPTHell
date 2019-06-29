//회원가입 창 UI ServerManager
package server.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import server.vo.User;

@Repository
public class JoinUIServerManager {
	
	@Autowired
	private ConnectionManager connectionManager;
	
	//회원가입 메소드, 가입 명령 실행 결과를 boolean 값으로 반환!
	public boolean join(User user) {
		boolean result = false;
		Connection con = connectionManager.getConnection();
		//새로운 User를 Userinfo table에 추가
		try {
			String sql = "insert into userinfo(userid,userpw,pwhint,grade,insertdate,USERNO) values(?,?,?,?,sysdate,SEQ_USERNO.NEXTVAL)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getPwHint());
			pstmt.setString(4, user.getGrade());
			
			if(pstmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}

//DB 접속 드라이버 로딩 및 연결 객체 생성
package server.manager;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Component;

@Component
public class ConnectionManager {
	
	@Autowired
	private EmbeddedDatabase embeddedDatabase;

	/**
	 * EmbeddedDatabase로부터 Connection을 생성하여 반환한다
	 * @return Connection 객체
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			con = embeddedDatabase.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	/**
	 * Connection 연결을 종료한다 
	 * @param con Connection 객체
	 */
	public static void close(Connection con) {
		try {
			// 매개변수로 입력받은 Connection 객체가 null이 아니라면 종료한다
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

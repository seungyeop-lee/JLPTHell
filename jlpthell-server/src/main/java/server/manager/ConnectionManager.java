//DB 접속 드라이버 로딩 및 연결 객체 생성
package server.manager;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Component;

import config.ServerConfigration;

@Component
public class ConnectionManager {
	
	private static ApplicationContext context;

	// 생성자를 private으로 설정한다
	// 이유: 단 하나의 객체만 생성하고 다른 어떤 이도 이 클래스를 계속 생성할 수 없게 막기 위함
	private ConnectionManager() {}
		
	/**
	 * EmbeddedDatabase로부터 Connection을 생성하여 반환한다
	 * @return Connection 객체
	 */
	public static Connection getConnection() {
		EmbeddedDatabase embeddedDatabase = getContext().getBean(EmbeddedDatabase.class);
		
		Connection con = null;
		try {
			con = embeddedDatabase.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	private static ApplicationContext getContext() {
		if(context == null) {
			context = new AnnotationConfigApplicationContext(ServerConfigration.class);
		}
		return context;
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

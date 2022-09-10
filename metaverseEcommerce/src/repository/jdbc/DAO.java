package repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	/*
	 * Field
	 */
	// DB configuration
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "carrot";
	private String pwd = "carrot";
	// 공통 사용 필드
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	


	/*
	 * Method
	 */
	public void connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

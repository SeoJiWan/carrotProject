package repository.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
//	private void dbConfig() {
//		String src = "config/db.properties";
//		Properties properties = new Properties();
//		
//		try {
//			String path = ClassLoader.getSystemClassLoader().getResource(src).getPath();
//			properties.load(new FileInputStream(path));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		driver = properties.getProperty("driver");
//		url = properties.getProperty("url");
//		id = properties.getProperty("id");
//		pwd = properties.getProperty("pwd");
//	}
	
	public void connect() {
//		dbConfig();
		
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

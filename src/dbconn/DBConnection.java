package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection con;
	
	private DBConnection()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "solar", "1234");
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 연결 실패" + e);
		}
	}
	
	public static Connection getConnection()
	{
		if (con == null)
		{
			System.out.println("최초 생성");
			new DBConnection();
		}
		
		return con;
	}
}

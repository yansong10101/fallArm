package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

	public static String CONNECTION = "jdbc:mysql://127.0.0.1:3306/";
	public static String dbs = "mydatabase";
	public static String dbUser = "root";
	public static String dbPasswd = "";
	public static Connection conn = null;
	
	public static void setConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Properties pro = new Properties();
		pro.put("user", dbUser);
		pro.put("password", dbPasswd);
		conn = DriverManager.getConnection(CONNECTION + dbs, pro);
		System.out.println("Connet to the MySql");		
	}
	
	public static void stopConnection() throws SQLException {
		conn.close();
		System.out.println("close Mysql connection");
	}
	
}

package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			return DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=ApParty", "sa", "qwerty");
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}

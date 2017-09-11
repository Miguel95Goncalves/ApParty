package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import services.SUser;

public class UserSQL {

	public static int login(String user_email, String user_password) { // Login
		String login = "SELECT user_id, user_user_type_id" + " FROM Users"
				+ " LEFT JOIN Status ON status_id = user_status_id"
				+ " LEFT JOIN Tables ON table_id = status_table_id" + " WHERE user_email = '" + user_email
				+ "'" + " AND user_password = '" + SUser.EncryptPwd(user_password) + "'"
				+ " AND user_status_id = (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Enabled')"
				+ " AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(login);

			if (rs.next()) {
				return rs.getInt("user_id");
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! LoginQuery");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static int userType(int user_id) { // Retornar o id do tipo de utilizador com base no id de utilizador
		String userType = "SELECT user_user_type_id" + " FROM Users"
				+ " WHERE user_id = " + user_id + "";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userType);

			if (rs.next()) {
				return rs.getInt("user_user_type_id");
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! userTypeQuery");
			System.err.println(e.getMessage());
		}

		return 0;
	}
	
	public static int userTypeClient() { // Retornar id onde o tipo de utilizador é "Client"
		String userType = "SELECT user_type_id" + " FROM User_Type"
				+ " WHERE user_type_name = 'Client'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userType);

			if (rs.next()) {
				return rs.getInt("user_type_id");
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! userTypeQuery");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static int userStatusEnabled() { // Retornar id onde o estado do utilizador é "Enabled"
		String userStatus = "SELECT status_id" + " FROM Status"
	+ " LEFT JOIN Tables ON table_id = status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userStatus);

			if (rs.next()) {
				return rs.getInt("status_id");
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! userStatusQuery");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static int userTypeProfessional() { // Retornar id onde o tipo de utilizador é "Professional"
		String userType = "SELECT user_user_type_id" + " FROM User_Type"
				+ " WHERE user_type_name = 'Professional'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userType);

			if (rs.next()) {
				return rs.getInt("user_user_type_id");
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! userTypeQuery");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static int userStatusDisabled() { // Retornar id onde o estado do utilizador é "Disabled"
		String userStatus = "SELECT status_id" + " FROM Status"
	+ " LEFT JOIN Tables ON table_id = status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Disabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userStatus);

			if (rs.next()) {
				return rs.getInt("status_id");
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! userStatusQuery");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static String userPassword(int user_id) { // Retorna a password
		String userPassword = "SELECT user_password" + " FROM Users"
							+ " WHERE user_id = " + user_id + "";

					try {
						Connection conn = DBConnection.getConnection();

						Statement st = conn.createStatement();
						ResultSet rs;

						rs = st.executeQuery(userPassword);

						if (rs.next()) {
							return SUser.decryptPwd(rs.getString("user_password"));
						}

						conn.close();

					} catch (Exception e) {
						System.err.println("Got an exception! userPasswordQuery");
						System.err.println(e.getMessage());
					}

					return "";
	}
}

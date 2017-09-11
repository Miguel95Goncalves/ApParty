package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.UserProfessional;
import services.Logic;
import services.SUser;
import services.SUserType;

public class UserProfessionalSQL {

	SUser sUser = new SUser();

	public static void loadUserProfessional() { // Carregar profissionais
		String userProfessional = "SELECT user_id, user_name, user_email, user_contact, user_description, user_user_type_id"
				+ " FROM Users" + " LEFT JOIN Status ON Status.status_id = Users.user_status_id"
				+ " LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userProfessional);

			while (rs.next()) {
				Logic.arUserProfessional.add(new UserProfessional(rs.getInt("user_id"),
						new SUserType().searchUserType(rs.getInt("user_type_id")), rs.getString("user_name"),
						rs.getString("user_email"), rs.getString("user_contact")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserProfessional");
			System.err.println(e.getMessage());
		}

	}

	public static int insertUserProfessional(String user_name, String user_email, String user_password, // Inserir profissional
			String user_contact, String user_description, int user_type_id, int user_status_id) {
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Users(user_name, user_email, user_password, user_nick, user_contact, user_birth, user_avatar, user_description, user_type_id, user_status_id)"
							+ " VALUES ('" + user_name + ", " + user_email + ", " + SUser.EncryptPwd(user_password)
							+ ", " + user_contact + ", " + user_description + ", " + user_type_id + ", "
							+ user_status_id + "')");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertUserProfessional");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String userProfessional = "SELECT MAX(user_id) AS max_id FROM Users"; // Query para ir buscar o ID maximo

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userProfessional);

			while (rs.next()) {
				if (rs.getInt("max_id") >= 1) { // Se o ID retornado pela query for maior ou igual a 1, este metodo ira
												// retornar esse valor
					return rs.getInt("max_id");
				} else { // Caso contrario ira retornar 1
					return 1;
				}
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! userProfessional Max ID");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static void editUserProfessional(int user_id, String user_name, String user_email, String user_password, // Editar profissional
			String user_contact, String user_description, int user_type_id, int user_status_id) {
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Users" + " SET user_name = '" + user_name + "', user_email = '" + user_email
					+ "', user_password = '" + SUser.EncryptPwd(user_password) + "'," + " user_contact = '"
					+ user_contact + "', user_description = '" + user_description + "'" + " WHERE user_id = " + user_id
					+ "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! editUserProfessional");
			System.err.println(e.getMessage());
		}
	}

	public static void delUserProfessional(int user_id) { // Remover Profissional
		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE User"
					+ " SET user_status_id = (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Users' AND status_name = 'Disabled')" + " WHERE user_id = " + user_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! delUserProfessional");
			System.err.println(e.getMessage());
		}
	}
}

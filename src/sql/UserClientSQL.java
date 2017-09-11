package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Party;
import model.UserClient;
import model.UserParty;
import services.Logic;
import services.SUser;
import services.SUserType;

public class UserClientSQL {

	public static void loadUserClient() { // Carregar clientes
		String userClient = "SELECT user_id, user_name, user_email, user_nick, user_contact, user_birth, user_avatar, user_description, user_user_type_id"
				+ " FROM Users" + " LEFT JOIN Status ON Status.status_id = Users.user_status_id"
				+ " LEFT JOIN Db_Tables ON Db_Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

			while (rs.next()) {
				Logic.arUserClient.add(
						new UserClient(rs.getInt("user_id"), new SUserType().searchUserType(rs.getInt("user_type_id")),
								rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_contact"),
								rs.getString("user_nick"), rs.getString("user_birth"), rs.getString("user_avatar")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserClient");
			System.err.println(e.getMessage());
		}
	}

	public static void loadUsersToParty() { // Carregar utilizadores (clientes) para festas

		for (Party p : Logic.arParty) {
			String userClient = "SELECT up_id, up_user_id" + " FROM User_Party" + " WHERE up_party_id = "
					+ p.getParty_id() + "";

			try {
				Connection conn = DBConnection.getConnection();

				Statement st = conn.createStatement();
				ResultSet rs;

				rs = st.executeQuery(userClient);

				while (rs.next()) {
					p.getPartyArUser().add(new UserParty(rs.getInt("up_id"), rs.getInt("up_user_id")));
				}
				conn.close();

			} catch (Exception e) {
				System.err.println("Got an exception! loadUserClientsToParty");
				System.err.println(e.getMessage());
			}
		}
	}

	public static void loadFriendInvite() { // Carregar pedidos de amizade
		String userClient = "SELECT fi_invite_id, fi_user_id";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

			while (rs.next()) {
				Logic.arUserClient.add(
						new UserClient(rs.getInt("user_id"), new SUserType().searchUserType(rs.getInt("user_type_id")),
								rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_contact"),
								rs.getString("user_nick"), rs.getString("user_birth"), rs.getString("user_avatar")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserClient");
			System.err.println(e.getMessage());
		}
	}

	public static int insertUserClient(String user_name, String user_email, String user_password, String user_nick, // Inserir Cliente
			String user_contact, String user_birth, String user_avatar, String user_description, int user_type_id,
			int user_status_id) {

		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Users(user_name, user_email, user_password, user_nick, user_contact, user_birth, user_avatar, user_description, user_type_id, user_status_id),"
							+ " VALUES ('" + user_name + "', '" + user_email + "', '" + SUser.EncryptPwd(user_password)
							+ "'," + " '" + user_nick + "', '" + user_contact + "', '" + user_birth + "', '"
							+ user_avatar + "'," + " '" + user_description + "', " + user_type_id + ","
							+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
							+ " WHERE table_name = 'Users' AND status_name = 'Enabled'))");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertUserClient");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String userClient = "SELECT MAX(user_id) AS max_id FROM Users"; // Query para ir buscar o ID maximo

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

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
			System.err.println("Got an exception! userClient Max ID");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static void editUserClient(int user_id, String user_name, String user_email, String user_password, // Editar Cliente
			String user_nick, String user_contact, String user_birth, String user_avatar, String user_description,
			int user_type_id, int user_status_id) {
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Users" + " SET user_name = '" + user_name + "', user_email = '" + user_email
					+ "', user_password = '" + SUser.EncryptPwd(user_password) + "'," + " user_nick = '" + user_nick
					+ "', user_contact = '" + user_contact + "', user_birth = '" + user_birth + "'," + "user_avatar = '"
					+ user_avatar + "', user_description = '" + user_description + "'" + " WHERE user_id = " + user_id
					+ "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! EditUser");
			System.err.println(e.getMessage());
		}
	}

	public static void delUserClient(int user_id) { // Remover Cliente
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Users"
					+ " SET user_status_id = (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Users' AND status_name = 'Disabled')" + " WHERE user_id = " + user_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! delUserClient");
			System.err.println(e.getMessage());
		}
	}

}
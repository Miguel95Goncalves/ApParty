package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import db_connection.DBConnection;
import model.Friend;
import model.Party;
import model.UserClient;
import model.UserParty;
import services.Logic;
import services.SStatus;
import services.SUser;
import services.SUserType;

public class UserClientSQL {

	public static void loadUserClient() { // Carregar clientes
		String userClient = "SELECT user_id, user_name, user_email, user_nick, user_contact, user_birth, user_avatar, user_description, user_user_type_id"
				+ " FROM Users" + " LEFT JOIN Status ON Status.status_id = Users.user_status_id"
				+ " LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

			while (rs.next()) {
				Logic.arUserClient.add(new UserClient(rs.getInt("user_id"),
						new SUserType().searchUserType(rs.getInt("user_user_type_id")), rs.getString("user_name"),
						rs.getString("user_email"), rs.getString("user_contact"), rs.getString("user_nick"),
						rs.getString("user_birth"), rs.getString("user_avatar")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserClient");
			System.err.println(e.getMessage());
		}
	}

	public static void loadUsersToParty() { // Carregar utilizadores (clientes) para festas

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		LocalDate localDate = LocalDate.now();

		// String date = String.copyValueOf(dtf.format(localDate));

		for (Party p : Logic.arParty) {
			String userClient = "SELECT up_id, up_user_id" + " FROM User_Party"
					+ " LEFT JOIN Party ON party_id = up_party_id" + " WHERE up_party_id = " + p.getParty_id()

					+ " AND '" + dtf.format(localDate) + "' > party_date";

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
		String userClient = "SELECT friend_id, friend_user_id, friend_friend_id, friend_status_id" + " FROM Friend"
				+ " LEFT JOIN Status ON status_id = friend_status_id" + " WHERE status_name = 'Invited'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

			while (rs.next()) {
				SUser.searchUserClient(rs.getInt("friend_friend_id")).getUserArFriendInvite()
						.add(new Friend(rs.getInt("friend_id"), SUser.searchUserClient(rs.getInt("friend_user_id")),
								SStatus.searchStatus(rs.getInt("friend_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadFriendInvite");
			System.err.println(e.getMessage());
		}
	}

	public static ArrayList<UserClient> loadCommonPeople(int user_id) { // Carregar common people

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		LocalDate localDate = LocalDate.now();

		ArrayList<UserClient> arCommonPeople = new ArrayList<>();

		String commonPeople = "SELECT user1.user_id, user1.user_name, user_email, user_contact, user_nick, user_birth, user_avatar, user_type_id" + 
				" from Users as user1" + 
				" join User_Party on up_user_id=user1.user_id" + 
				" join Party on party_id = up_party_id" + 
				" join User_Type on user_type_id = user_user_type_id" + 
				" where user_id!=" + user_id + " And user_id not in " + 
				" (SELECT friend_user_id" + 
				" FROM Friend" + 
				" WHERE friend_user_id = " + user_id + " OR friend_friend_id = " + user_id + ")" + 
				" and user_id not in" + 
				" (SELECT friend_friend_id" + 
				" FROM Friend" + 
				" WHERE friend_user_id = " + user_id + " OR friend_friend_id = " + user_id + ")" + 
				" GROUP BY user1.user_id, user1.user_name, user_email, user_contact, user_nick, user_birth, user_avatar, user_type_id";
		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(commonPeople);

			while (rs.next()) {
				arCommonPeople.add(new UserClient(rs.getInt("user_id"),
						new SUserType().searchUserType(rs.getInt("user_type_id")), rs.getString("user_name"),
						rs.getString("user_email"), rs.getString("user_contact"), rs.getString("user_nick"),
						rs.getString("user_birth"), rs.getString("user_avatar")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadCommonPeople");
			System.err.println(e.getMessage());
		}

		return arCommonPeople;
	}

	public static ArrayList<Party> loadCommonPartys(int user_id, HttpServletRequest req) {

		HttpSession sessao = req.getSession(true);

		ArrayList<Party> arCommonPartys = new ArrayList<>();

		String commonPartys = "SELECT party_id, party_duration, party_price, party_name, party_description, party_date, party_coord, party_location, party_start, party_status_id\r\n"
				+ "FROM Party\r\n" + "LEFT JOIN User_Party ON party_id = up_party_id\r\n" + "WHERE up_user_id = "
				+ user_id
				+ " AND party_id IN (SELECT party_id FROM Party LEFT JOIN User_Party ON up_party_id = party_id WHERE up_user_id = "
				+ (int) sessao.getAttribute("user_id") + ")"
				+ "GROUP BY party_id, party_duration, party_price, party_name, party_description, party_date, party_coord, party_location, party_start, party_status_id";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			UserClient uc = new UserClient();

			rs = st.executeQuery(commonPartys);

			while (rs.next()) {
				arCommonPartys.add(new Party(rs.getInt("party_id"), rs.getInt("party_duration"),
						rs.getFloat("party_price"), rs.getString("party_name"), rs.getString("party_description"),
						rs.getString("party_date"), rs.getString("party_coord"), rs.getString("party_location"),
						rs.getString("party_start"), uc, SStatus.searchStatus(rs.getInt("party_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadCommonParty");
			System.err.println(e.getMessage());
		}

		return arCommonPartys;
	}

	public static int insertUserClient(String user_name, String user_email, String user_password, String user_nick, // Inserir
																													// Cliente
			String user_contact, String user_birth, String user_avatar, String user_description, int user_type_id,
			int user_status_id) {

		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Users(user_name, user_email, user_password, user_nick, user_contact, user_birth, user_avatar, user_description, user_user_type_id, user_status_id)"
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

	public static void editUserClient(int user_id, String user_name, String user_email, String user_password, // Editar
																												// Cliente
			String user_nick, String user_contact, String user_birth, String user_avatar, String user_description) {
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
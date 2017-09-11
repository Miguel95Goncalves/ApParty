package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Friend;
import model.UserClient;
import services.Logic;
import services.SStatus;
import services.SUser;

public class FriendSQL {

	public static void loadUsersFriends() { // Carregar listas de amigos

		SUser sUser = new SUser();
		SStatus sStatus = new SStatus();

		for (UserClient uc : Logic.arUserClient) {

			String friend = "SELECT friend_id, friend_friend_id, friend_status_id" + " FROM Friend"
					+ " WHERE friend_user_id = " + uc.getUser_id();

			try {
				Connection conn = DBConnection.getConnection();

				Statement st = conn.createStatement();
				ResultSet rs;

				rs = st.executeQuery(friend);

				while (rs.next()) {
					uc.getUserArFriend()
							.add(new Friend(rs.getInt("friend_id"),
									sUser.searchUserClient(rs.getInt("friend_friend_id")),
									sStatus.searchStatus(rs.getInt("friend_status_id"))));
				}
				conn.close();

			} catch (Exception e) {
				System.err.println("Got an exception! loadUserFriends");
				System.err.println(e.getMessage());
			}

			// ------------------------------------------------------

			friend = "SELECT friend_id, friend_user_id, friend_status_id" + " FROM Friend" + " WHERE friend_friend_id = "
					+ uc.getUser_id() + "";

			try {
				Connection conn = DBConnection.getConnection();

				Statement st = conn.createStatement();
				ResultSet rs;

				rs = st.executeQuery(friend);

				while (rs.next()) {
					uc.getUserArFriend()
							.add(new Friend(rs.getInt("friend_id"), sUser.searchUserClient(rs.getInt("friend_user_id")),
									sStatus.searchStatus(rs.getInt("friend_status_id"))));
				}
				conn.close();

			} catch (Exception e) {
				System.err.println("Got an exception! loadUserFriends");
				System.err.println(e.getMessage());
			}

		}

	}

	public static int addFriend(int friend_user_id, int friend_friend_id) { // Adicionar amigo
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("INSERT INTO Friend(friend_user_id, friend_friend_id, friend_status_id)" + " VALUES ('"
					+ friend_user_id + ", " + friend_friend_id + ","
					+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Friend' AND status_name = 'Invited'),");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertFriend");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String friend = "SELECT MAX(friend_id) AS max_id FROM Friend";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(friend);

			while (rs.next()) {
				if (rs.getInt("max_id") >= 1) {

					return rs.getInt("max_id");
				} else {
					return 1;
				}
			}

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! friendMaxID");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static void acceptFriend(int friend_id) { // Aceitar amigo
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Friend" + " SET friend_status_id ="
					+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Friend' AND status_name = 'Accepted')" + " WHERE friend_id = " + friend_id
					+ "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! AcceptUser");
			System.err.println(e.getMessage());
		}
	}

	public static void delFriendship(int friendship_id) { // Apagar relação de amizade
		String delFriendship = "DELETE FROM Friend" + " WHERE friend_id = " + friendship_id + "";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(delFriendship);
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! delFriendship");
			System.err.println(e.getMessage());
		}
	}

}

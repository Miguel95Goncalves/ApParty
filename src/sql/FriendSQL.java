package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Friend;
import services.SUser;

public class FriendSQL {
	
	public static void loadUserClient(){
		String friend = "SELECT friend_id, friend_user_id, friend_friend_id FROM Friend";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(friend);
			
			SUser sUser = new SUser();

			while (rs.next()) {
				sUser.searchUserClient(rs.getInt("friend_user_id")).getUserArFriend().add(new Friend(
						rs.getInt("friend_id"),
						sUser.searchUserClient(rs.getInt("friend_friend_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserClient");
			System.err.println(e.getMessage());
		}
		
	}
	
	public static void delFriend(int friend_id){ //Apagar relação de amizade
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"DELETE FROM Friend"
						+ " WHERE friend_id = " + friend_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

}

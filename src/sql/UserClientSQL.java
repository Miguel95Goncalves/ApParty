package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.UserClient;
import services.Logic;
import services.SUser;
import services.SUserType;

public class UserClientSQL {

	public static void loadUserClient(){
		String userClient = "SELECT user_id, user_name, user_email, user_nick, user_contact, user_birth, user_avatar, user_description, user_user_type_id" + 
				" FROM Users" + 
				" LEFT JOIN Status ON Status.status_id = Users.user_status_id" + 
				" LEFT JOIN Db_Tables ON Db_Tables.table_id = Status.status_table_id" + 
				" WHERE table_name = 'Users'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

			while (rs.next()) {
				Logic.arUserClient.add(new UserClient(rs.getInt("user_id"),
						new SUserType().searchUserType(rs.getInt("user_type_id")),
						rs.getString("user_name"),
						rs.getString("user_email"),
						rs.getString("user_contact"),
						rs.getString("user_nick"),
						rs.getString("user_birth"),
						rs.getString("user_avatar")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserClient");
			System.err.println(e.getMessage());
		}
		
	}
	
	public static int insertUserClient(String user_name,String user_email,String user_password,String user_nick,String user_contact,String user_birth,
			String user_avatar,String user_description,int user_type_id,int user_status_id) {
		
		SUser sUser = new SUser();
		
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Users(user_name, user_email, user_password, user_nick, user_contact, user_birth, user_avatar, user_description, user_type_id, user_status_id)"
						+ " VALUES ('" + user_name + ", " + user_email + ", " + sUser.EncryptPwd(user_password) + ", " + user_nick + ", " + user_contact + ", " + user_birth + ", " + user_avatar + ", "
								+ user_description + ", " + user_type_id + ", " + user_status_id + "')");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertUserClient");
			System.err.println(e.getMessage());
		}
		
		//-----------------------//-----------------------//
		
		String userClient = "SELECT MAX(user_id) AS max_id FROM Users"; //Query para ir buscar o ID maximo
		
		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userClient);

			while(rs.next()) {
				if(rs.getInt("max_id") >= 1) { //Se o ID retornado pela query for maior ou igual a 1, este metodo ira retornar esse valor
					return rs.getInt("max_id");
				}else { //Caso contrario ira retornar 1
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
	
	public static void editUserClient(int user_id, String user_name,String user_email,String user_password,String user_nick,String user_contact,
			String user_birth,String user_avatar,String user_description,int user_type_id,int user_status_id){
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"UPDATE Users"
					+ " SET user_name = '" + user_name + "', user_email = '" + user_email + "', user_password = '" + user_password + "',"
							+ " user_nick = '" + user_nick + "', user_contact = '" + user_contact + "', user_birth = '" + user_birth + "',"
									+ "user_avatar = '" + user_avatar + "', user_description = '" + user_description + "'"
							+ " WHERE user_id = " + user_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void delUserClient(int user_id){
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"UPDATE User"
					+ " SET user_status_id = 3"
							+ " WHERE user_id = " + user_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static int login(String user_email, String user_password) {
		String login = "SELECT user_id, user_type_id FROM Users WHERE user_email = '" + user_email
				+ "' AND user_password = '" + user_password + "' AND user_status_id = 1";

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
			System.err.println("Got an exception! UserQuerys.login()");
			System.err.println(e.getMessage());
		}

		return 0;
	}
}
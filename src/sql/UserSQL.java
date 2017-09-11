package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import services.SUser;

public class UserSQL {

	public static int login(String user_email, String user_password) { // Login
		String login = "SELECT user_id, user_user_type_id" + " FROM Users"
				+ " LEFT JOIN Status ON Status.status_id = Users.user_status_id"
				+ " LEFT JOIN Tables ON Tables.table_id = Status.status_table_id" + " WHERE user_email = '" + user_email
				+ "'" + " AND user_password = '" + SUser.EncryptPwd(user_password) + "'"
				+ " AND user_status_id = (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Users' AND status_name = 'Enabled') AND" + "table_name = 'Users'"
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

}

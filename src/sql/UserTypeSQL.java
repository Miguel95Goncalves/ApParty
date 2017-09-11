package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.UserType;
import services.Logic;

public class UserTypeSQL {
	public static void loadUserType() { // Carregar tipos de utilizador
		String userTypes = "SELECT user_type_id, user_type_name FROM User_Type";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(userTypes);

			while (rs.next()) {
				Logic.arUserType.add(new UserType(rs.getInt("user_type_id"), rs.getString("user_type_name")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadUserType");
			System.err.println(e.getMessage());
		}
	}
}

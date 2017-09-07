package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Privilege;
import services.Logic;
import services.SStatus;

public class PrivilegeSQL {
	public static void loadPrivilege() {
		String privileges = "SELECT privilege_id, privilege_days, privilege_price, privilege_status_id FROM Privilege";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(privileges);
			
			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arPrivilege.add(new Privilege(rs.getInt("privilege_id"), rs.getInt("privilege_days"), rs.getFloat("privilege_price"), sStatus.searchStatus(rs.getInt("privilege_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadPrivileges");
			System.err.println(e.getMessage());
		}
	}
}

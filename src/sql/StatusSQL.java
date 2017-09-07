package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Status;
import services.Logic;
import services.STable;

public class StatusSQL {
	public static void loadStatus() {
		String status = "SELECT status_id, status_name, status_table_id FROM Status";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(status);
			
			STable sTable = new STable();

			while (rs.next()) {
				Logic.arStatus.add(new Status(rs.getInt("status_id"), rs.getString("status_name"), sTable.searchTable(rs.getInt("status_table_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadStatus");
			System.err.println(e.getMessage());
		}
	}
}

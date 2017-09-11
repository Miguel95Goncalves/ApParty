package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Table;
import services.Logic;

public class TableSQL {

	public static void loadTables() { // Carregar Tabelas
		String tabelas = "SELECT table_id, table_name FROM Tables";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(tabelas);

			while (rs.next()) {
				Logic.arTable.add(new Table(rs.getInt("table_id"), rs.getString("table_name")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadTables");
			System.err.println(e.getMessage());
		}
	}
}

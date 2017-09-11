package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Privilege;
import model.ServicePrivilege;
import services.Logic;
import services.SPrivilege;
import services.SService;
import services.SStatus;

public class PrivilegeSQL {
	public static void loadPrivilege() { // Carregar privilégios
		String privileges = "SELECT privilege_id, privilege_days, privilege_price, privilege_status_id"
				+ " FROM Privilege" + " LEFT JOIN Status ON Status.status_id = Privilege.privilege_status_id"
				+ " LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Privilege' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(privileges);

			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arPrivilege.add(new Privilege(rs.getInt("privilege_id"), rs.getInt("privilege_days"),
						rs.getFloat("privilege_price"), sStatus.searchStatus(rs.getInt("privilege_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadPrivileges");
			System.err.println(e.getMessage());
		}
	}

	public static void loadPrivilegeToServices() { // Carregar privilégios para os serviços
		String privileges = "SELECT sp_id, sp_service_id, sp_initial_date, sp_final_date" + " FROM Service_Privilege"
				+ " LEFT JOIN Status ON Status.status_id = Service_Privilege.sp_status_id"
				+ " LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Service_Privilege' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(privileges);

			SStatus sStatus = new SStatus();

			while (rs.next()) {
				SService.searchService(rs.getInt("sp_service_id")).getServiceArPrivilege()
						.add(new ServicePrivilege(rs.getInt("sp_id"),
								SPrivilege.searchPrivilege(rs.getInt("sp_privilege_id")),
								rs.getString("sp_initial_date"), rs.getString("sp_final_date"),
								sStatus.searchStatus(rs.getInt("sp_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadPrivilegeToServices");
			System.err.println(e.getMessage());
		}
	}
}

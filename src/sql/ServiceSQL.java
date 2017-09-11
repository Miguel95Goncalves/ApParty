package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Party;
import model.PartyService;
import model.Service;
import services.Logic;
import services.SCategory;
import services.SStatus;

public class ServiceSQL {

	public static void loadService() { // Carregar serviços
		String services = "SELECT service_id, service_name, service_tiny_description, service_full_description, service_images, service_user_id, service_status_id, service_category_id"
				+ " FROM Services" + " LEFT JOIN Status ON Status.status_id = Services.service_status_id"
				+ " LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Services' AND status_name = 'Enabled'";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(services);

			SStatus sStatus = new SStatus();
			SCategory sCategory = new SCategory();

			while (rs.next()) {
				Logic.arService.add(new Service(rs.getInt("service_id"), rs.getString("service_name"),
						rs.getString("service_tiny_description"), rs.getString("service_full_description"),
						rs.getString("service_images"), sStatus.searchStatus(rs.getInt("service_status_id")),
						sCategory.searchCategory(rs.getInt("service_category_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadServices");
			System.err.println(e.getMessage());
		}
	}

	public static void loadServiceToPartys() { // Carregar serviços para as festas

		for (Party p : Logic.arParty) {
			String services = "SELECT ps_id, ps_service_id" + " FROM Party_Service" + " WHERE ps_party_id = "
					+ p.getParty_id() + "";

			try {
				Connection conn = DBConnection.getConnection();

				Statement st = conn.createStatement();
				ResultSet rs;

				rs = st.executeQuery(services);

				while (rs.next()) {
					p.getPartyArService().add(new PartyService(rs.getInt("ps_id"), rs.getInt("ps_service_id")));
				}
				conn.close();

			} catch (Exception e) {
				System.err.println("Got an exception! loadServicesToPartys");
				System.err.println(e.getMessage());
			}
		}

	}

	public static int insertService(String service_name, String service_tiny_description, // Inserir Serviço
			String service_full_description, String service_images, int service_category_id, int service_user_id) {

		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Services(service_name, service_tiny_description, service_full_description, service_images, service_user_id, service_status_id, service_category_id)"
							+ " VALUES ('" + service_name + ", " + service_tiny_description + ", "
							+ service_full_description + ", " + service_images + ", " + service_user_id + ","
							+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
							+ " WHERE table_name = 'Services' AND status_name = 'Enabled')," + " " + service_category_id
							+ "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertService");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String service = "SELECT MAX(service_id) AS max_id FROM Services"; // Query para ir buscar o ID maximo

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(service);

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
			System.err.println("Got an exception! serviceMaxID");
			System.err.println(e.getMessage());
		}

		return 0;
	}

	public static void editService(int service_id, String service_name, String service_tiny_description, // Editar Serviço
			String service_full_description, String service_images, int service_category_id, int service_user_id) {

		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Services" + " SET service_name = '" + service_name
					+ "', service_tiny_description = '" + service_tiny_description + "', service_full_description = '"
					+ service_full_description + "'," + " service_images = '" + service_images
					+ "', service_user_id = '" + service_user_id + "', service_category_id = '" + service_category_id
					+ "'" + " WHERE service_id = " + service_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! EditService");
			System.err.println(e.getMessage());
		}
	}

	public static void delService(int service_id) { // Apagar Serviço
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Services" + " SET service_status_id ="
					+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Services' AND status_name = 'Disabled')" + " WHERE service_id = "
					+ service_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! delService");
			System.err.println(e.getMessage());
		}
	}
}

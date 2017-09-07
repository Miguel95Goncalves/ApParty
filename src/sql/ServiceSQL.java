package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Party;
import model.Service;
import services.Logic;
import services.SPrivilege;
import services.SStatus;
import services.SUser;

public class ServiceSQL {
	
	//Carregar servicos de festas
	//carregar servicos disponiveis

	public static void loadService(){
		String service = "SELECT service_id, service_name, service_tiny_description, service_full_description, service_images, service_user_id, service_status_id, service_category_id"
				+ " FROM Services"
				+ " WHERE service_status_id = 11";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(service);
			
			SUser sUser = new SUser();
			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arService.add(new Service(rs.getInt("service_id"), rs.getString("service_name"), rs.getString("service_tiny_description"), rs.getString("service_full_description"), rs.getString("service_images"), sStatus.searchStatus(rs.getInt("service_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadParty");
			System.err.println(e.getMessage());
		}
	}
	
	
}

package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Party;
import model.Service;
import services.Logic;
import services.SStatus;
import services.SUser;

public class ServiceSQL {
	
	//Carregar servicos de festas
	//carregar servicos disponiveis

	public void loadService(){
		String service = "SELECT service_id, service_name, service_description, service_images, service_privilege_id, service_user_id, service_privilege_final_date, service_status_id"
				+ " FROM Service"
				+ " WHERE service_status_id = ?"; //Corrigir

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(service);
			
			SUser sUser = new SUser();
			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arService.add(new Service());
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadParty");
			System.err.println(e.getMessage());
		}
	}
	
}

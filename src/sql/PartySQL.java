package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Party;
import model.PartyStage;
import services.Logic;
import services.SParty;
import services.SStatus;
import services.SUser;

public class PartySQL {

	public static void loadParty() {
		String party = "SELECT party_id, party_name, party_description, party_date, party_coord, party_location, party_start, party_duration, party_qtd_people, party_user_id, party_price, party_status_id"
				+ " FROM Party"
				+ " WHERE party_status_id = 5";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(party);
			
			SUser sUser = new SUser();
			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arParty.add(new Party(rs.getInt("party_id"), rs.getInt("party_duration"), rs.getFloat("party_price"), rs.getString("party_name"), rs.getString("party_description"), 
						rs.getString("party_date"), rs.getString("party_coord"), rs.getString("party_location"), rs.getString("party_start"),
						sUser.searchUserClient(rs.getInt("party_user_id")),
						sStatus.searchStatus(rs.getInt("party_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadParty");
			System.err.println(e.getMessage());
		}
	}
	
	public static void loadPartyStage() {
		String partyStage = "SELECT party_stage_id, party_stage_description, party_stage_party_id"
				+ " FROM Party_Stage";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(partyStage);
			
			SParty sParty = new SParty();

			while (rs.next()) {
				sParty.searchParty(rs.getInt("party_stage_party_id")).getPartyArStage().add(new PartyStage(rs.getInt("party_stage_id"), rs.getString("party_stage_description")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadPartyStage");
			System.err.println(e.getMessage());
		}
	}
	
	public static int insertParty(int party_id, String party_name, String party_description, String party_date, String party_coord, String party_location, String party_start, 
			int party_duration, int party_qtd_people, int party_user_id, float party_price, int party_status_id) {
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Party(party_id, party_name, party_description, party_date, party_coord, party_location, party_start, party_duration, party_qtd_people, party_user_id, party_price, party_status_id)"
						+ " VALUES ('" + party_id + ", " + party_name + ", " + party_description + ", " + party_date + ", " + party_coord + ", " + party_location + ", " + party_start + ", "
								+ party_duration + ", " + party_qtd_people + ", " + party_user_id + ", " + party_price + ", " + party_status_id + "')");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertParty");
			System.err.println(e.getMessage());
		}
		
		//-----------------------//-----------------------//
		
		String party = "SELECT MAX(party_id) AS max_id FROM Party"; //Query para ir buscar o ID maximo
		
		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(party);

			while(rs.next()) {
				if(rs.getInt("max_id") >= 1) { //Se o ID retornado pela query for maior ou igual a 1, este metodo ira retornar esse valor
					return rs.getInt("max_id");
				}else { //Caso contrario ira retornar 1
					return 1;
				}
			}
			
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! party Max ID");
			System.err.println(e.getMessage());
		}
		
		return 0;
	}

	public static int insertPartyStage(String party_stage_description, int party_stage_party_id){
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Party_Stage(party_stage_description, party_stage_party_id)"
						+ " VALUES ('" + party_stage_description + ", " + party_stage_party_id + "')");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertPartyStage");
			System.err.println(e.getMessage());
		}
		
		//-----------------------//-----------------------//
		
		String partyStage = "SELECT MAX(party_stage_id) AS max_id FROM Party_Stage"; //Query para ir buscar o ID maximo
		
		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(partyStage);

			while(rs.next()) {
				if(rs.getInt("max_id") >= 1) { //Se o ID retornado pela query for maior ou igual a 1, este metodo ira retornar esse valor
					return rs.getInt("max_id");
				}else { //Caso contrario ira retornar 1
					return 1;
				}
			}
			
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! partyStage Max ID");
			System.err.println(e.getMessage());
		}
		
		return 0;
	}
	
	public static void delParty(int party_id){
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"UPDATE Party"
					+ " SET party_status_id = 6"
							+ " WHERE party_id = " + party_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void delPartyStage(int party_stage_id){
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"DELETE FROM Party_Stage"
						+ " WHERE party_stage_id = " + party_stage_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
}

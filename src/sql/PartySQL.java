package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db_connection.DBConnection;
import model.Party;
import model.PartyInvite;
import model.PartyStage;
import services.Logic;
import services.SParty;
import services.SStatus;
import services.SUser;

public class PartySQL {

	public static void loadParty() { // Carregar festas
		String party = "SELECT party_id, party_name, party_description, party_date, party_coord, party_location, party_start, party_duration, party_qtd_people, party_user_id, party_price, party_status_id"
				+ " FROM Party" + " WHERE party_status_id ="
				+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
				+ " WHERE table_name = 'Party' AND status_name = 'Enabled')";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(party);

			SUser sUser = new SUser();
			SStatus sStatus = new SStatus();

			while (rs.next()) {
				Logic.arParty.add(new Party(rs.getInt("party_id"), rs.getInt("party_duration"),
						rs.getFloat("party_price"), rs.getString("party_name"), rs.getString("party_description"),
						rs.getString("party_date"), rs.getString("party_coord"), rs.getString("party_location"),
						rs.getString("party_start"), sUser.searchUserClient(rs.getInt("party_user_id")),
						sStatus.searchStatus(rs.getInt("party_status_id"))));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadParty");
			System.err.println(e.getMessage());
		}
	}

	public static void loadPartyStage() { // Carregar etapas das festas
		String partyStage = "SELECT party_stage_id, party_stage_description, party_stage_party_id"
				+ " FROM Party_Stage";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(partyStage);

			SParty sParty = new SParty();

			while (rs.next()) {
				sParty.searchParty(rs.getInt("party_stage_party_id")).getPartyArStage()
						.add(new PartyStage(rs.getInt("party_stage_id"), rs.getString("party_stage_description")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadPartyStage");
			System.err.println(e.getMessage());
		}
	}

	public static void loadPartyInvite() { // Carregar convites para as festas
		String partyInvite = "SELECT pi_invite_id, pi_guest_id, pi_party_id, pi_guest_status_id, pi_guest_name, pi_guest_email, pi_answer_date"
				+ " FROM Party_Invite";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(partyInvite);

			SParty sParty = new SParty();
			SStatus sStatus = new SStatus();

			while (rs.next()) {
				sParty.searchParty(rs.getInt("pi_party_id")).getPartyArPartyInvite()
						.add(new PartyInvite(rs.getString("pi_guest_email"), rs.getString("pi_guest_name"),
								sStatus.searchStatus(rs.getInt("pi_guest_status_id")), rs.getString("pi_answer_date")));
			}
			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! loadPartyInvite");
			System.err.println(e.getMessage());
		}
	}

	public static int insertParty(int party_id, String party_name, String party_description, String party_date, // Inserir festa
			String party_coord, String party_location, String party_start, int party_duration, int party_qtd_people,
			int party_user_id, float party_price, int party_status_id) {
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Party(party_id, party_name, party_description, party_date, party_coord, party_location, party_start, party_duration, party_qtd_people, party_user_id, party_price, party_status_id)"
							+ " VALUES ('" + party_id + ", " + party_name + ", " + party_description + ", " + party_date
							+ ", " + party_coord + ", " + party_location + ", " + party_start + ", " + party_duration
							+ ", " + party_qtd_people + ", " + party_user_id + ", " + party_price + ","
							+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
							+ " WHERE table_name = 'Party' AND status_name = 'Enabled')'");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertParty");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String party = "SELECT MAX(party_id) AS max_id FROM Party";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(party);

			while (rs.next()) {
				if (rs.getInt("max_id") >= 1) {

					return rs.getInt("max_id");
				} else {
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

	public static int insertPartyStage(String party_stage_description, int party_stage_party_id) { // Inserir etapa de festa
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("INSERT INTO Party_Stage(party_stage_description, party_stage_party_id)" + " VALUES ('"
					+ party_stage_description + ", " + party_stage_party_id + "')");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertPartyStage");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String partyStage = "SELECT MAX(party_stage_id) AS max_id FROM Party_Stage";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(partyStage);

			while (rs.next()) {
				if (rs.getInt("max_id") >= 1) {

					return rs.getInt("max_id");
				} else {
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

	public static int insertPartyInvite(int pi_guest_id, int pi_party_id, String pi_guest_name, String pi_guest_email, // Inserir Convite para festa
			String pi_answer_date) {
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate(
					"INSERT INTO Party_Invite(pi_guest_id, pi_party_id, pi_guest_name, pi_guest_email, pi_answer_date, pi_guest_status_id)"
							+ " VALUES ('" + pi_guest_id + ", " + pi_party_id + ", " + pi_guest_name + ", "
							+ pi_guest_email + ", " + pi_answer_date + ", "
							+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
							+ " WHERE table_name = 'Party_Invite' AND status_name = 'Invited')'");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! insertPartyInvite");
			System.err.println(e.getMessage());
		}

		// -----------------------//-----------------------//

		String partyInvite = "SELECT MAX(pi_invite_id) AS max_id FROM Party_Invite";

		try {
			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();
			ResultSet rs;

			rs = st.executeQuery(partyInvite);

			while (rs.next()) {
				if (rs.getInt("max_id") >= 1) {

					return rs.getInt("max_id");
				} else {
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

	public static void delParty(int party_id) { // Remover festa
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Party" + " SET party_status_id ="
					+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Party' AND status_name = 'Disabled')" + " WHERE party_id = " + party_id
					+ "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! delParty");
			System.err.println(e.getMessage());
		}
	}

	public static void delPartyStage(int party_stage_id) { // Remover etapa de festa
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("DELETE FROM Party_Stage" + " WHERE party_stage_id = " + party_stage_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! delPartyStage");
			System.err.println(e.getMessage());
		}
	}

	public static void acceptPartyInvite(int pi_invite_id) { // Aceitar convite de uma festa
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Party_Invite" + " SET pi_guest_status_id ="
					+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Party_Invite' AND status_name = 'Accepted')" + " WHERE pi_invite_id = "
					+ pi_invite_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! acceptPartyInvite");
			System.err.println(e.getMessage());
		}
	}

	public static void refusePartyInvite(int pi_invite_id) { // Recusar convite de uma festa
		try {

			Connection conn = DBConnection.getConnection();

			Statement st = conn.createStatement();

			st.executeUpdate("UPDATE Party_Invite" + " SET pi_guest_status_id ="
					+ " (SELECT status_id FROM Status LEFT JOIN Tables ON Tables.table_id = Status.status_table_id"
					+ " WHERE table_name = 'Party_Invite' AND status_name = 'Refused')" + " WHERE pi_invite_id = "
					+ pi_invite_id + "");

			conn.close();

		} catch (Exception e) {
			System.err.println("Got an exception! refusePartyInvite");
			System.err.println(e.getMessage());
		}
	}
}
package model;

import java.util.ArrayList;

public class Party {

	private int party_id, party_duration;
	private float party_price;
	private String party_name, party_description, party_date, party_coord, party_location, party_start;
	private User party_user;
	private ArrayList<PartyStage> partyArStage;
	private ArrayList<PartyService> partyArService;
	private ArrayList<UserClient> partyArUser;
	private Status party_status;

	public Party(int party_id, int party_duration, float party_price, String party_name, String party_description,
			String party_date, String party_coord, String party_location, String party_start, User party_user, Status party_status) {
		super();
		this.party_id = party_id;
		this.party_duration = party_duration;
		this.party_price = party_price;
		this.party_name = party_name;
		this.party_description = party_description;
		this.party_date = party_date;
		this.party_coord = party_coord;
		this.party_location = party_location;
		this.party_start = party_start;
		this.party_user = party_user;
		this.partyArStage = new ArrayList<>();
		this.partyArService = new ArrayList<>();
		this.partyArUser = new ArrayList<>();
		this.party_status = party_status;
	}

	public int getParty_id() {
		return party_id;
	}

	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}

	public int getParty_duration() {
		return party_duration;
	}

	public void setParty_duration(int party_duration) {
		this.party_duration = party_duration;
	}

	public float getParty_price() {
		return party_price;
	}

	public void setParty_price(float party_price) {
		this.party_price = party_price;
	}

	public String getParty_name() {
		return party_name;
	}

	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}

	public String getParty_description() {
		return party_description;
	}

	public void setParty_description(String party_description) {
		this.party_description = party_description;
	}

	public String getParty_date() {
		return party_date;
	}

	public void setParty_date(String party_date) {
		this.party_date = party_date;
	}

	public String getParty_coord() {
		return party_coord;
	}

	public void setParty_coord(String party_coord) {
		this.party_coord = party_coord;
	}

	public String getParty_location() {
		return party_location;
	}

	public void setParty_location(String party_location) {
		this.party_location = party_location;
	}

	public String getParty_start() {
		return party_start;
	}

	public void setParty_start(String party_start) {
		this.party_start = party_start;
	}

	public User getParty_user() {
		return party_user;
	}

	public void setParty_user(User party_user) {
		this.party_user = party_user;
	}

	public ArrayList<PartyStage> getPartyArStage() {
		return partyArStage;
	}

	public void setPartyArStage(ArrayList<PartyStage> partyArStage) {
		this.partyArStage = partyArStage;
	}

	public ArrayList<PartyService> getPartyArService() {
		return partyArService;
	}

	public void setPartyArService(ArrayList<PartyService> partyArService) {
		this.partyArService = partyArService;
	}
	
	public ArrayList<UserClient> getPartyArUser() {
		return partyArUser;
	}

	public void setPartyArUser(ArrayList<UserClient> partyArUser) {
		this.partyArUser = partyArUser;
	}

	public Status getParty_status() {
		return party_status;
	}

	public void setParty_status(Status party_status) {
		this.party_status = party_status;
	}

}

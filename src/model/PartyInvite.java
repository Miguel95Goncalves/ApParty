package model;

public class PartyInvite {

	private String pi_guest_email, pi_guest_name, pi_guest_status, pi_answer_date;
	private Party pi_party;
	private User pi_user_invited;
	
	public PartyInvite() {
		super();
	}
	
	public PartyInvite(User pi_user_invited) {
		super();
		this.pi_user_invited = pi_user_invited;
	}
	
	public PartyInvite(String pi_guest_email, String pi_guest_name, String pi_guest_status, String pi_answer_date,
			Party pi_party) {
		super();
		this.pi_guest_email = pi_guest_email;
		this.pi_guest_name = pi_guest_name;
		this.pi_guest_status = pi_guest_status;
		this.pi_answer_date = pi_answer_date;
		this.pi_party = pi_party;
	}
	
	public String getPi_guest_email() {
		return pi_guest_email;
	}
	public void setPi_guest_email(String pi_guest_email) {
		this.pi_guest_email = pi_guest_email;
	}
	public String getPi_guest_name() {
		return pi_guest_name;
	}
	public void setPi_guest_name(String pi_guest_name) {
		this.pi_guest_name = pi_guest_name;
	}
	public String getPi_guest_status() {
		return pi_guest_status;
	}
	public void setPi_guest_status(String pi_guest_status) {
		this.pi_guest_status = pi_guest_status;
	}
	public String getPi_answer_date() {
		return pi_answer_date;
	}
	public void setPi_answer_date(String pi_answer_date) {
		this.pi_answer_date = pi_answer_date;
	}
	public Party getPi_party() {
		return pi_party;
	}
	public void setPi_party(Party pi_party) {
		this.pi_party = pi_party;
	}
	public User getPi_user_invited() {
		return pi_user_invited;
	}
	public void setPi_user_invited(User pi_user_invited) {
		this.pi_user_invited = pi_user_invited;
	}

}

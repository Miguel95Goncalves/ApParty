package model;

public class FriendInvite {

	private int fi_invite_id;
	private User fi_user_invited;
	
	public FriendInvite() {
		super();
	}

	public FriendInvite(int fi_invite_id, User fi_user_invited) {
		super();
		this.fi_invite_id = fi_invite_id;
		this.fi_user_invited = fi_user_invited;
	}

	public int getFi_invite_id() {
		return fi_invite_id;
	}
	public void setFi_invite_id(int fi_invite_id) {
		this.fi_invite_id = fi_invite_id;
	}
	public User getFi_user_invited() {
		return fi_user_invited;
	}
	public void setFi_user_invited(User fi_user_invited) {
		this.fi_user_invited = fi_user_invited;
	}

}

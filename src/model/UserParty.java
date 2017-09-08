package model;

public class UserParty {
	private int up_id, up_user_id;

	public UserParty(int up_id, int up_user_id) {
		super();
		this.up_id = up_id;
		this.up_user_id = up_user_id;
	}

	public int getUp_id() {
		return up_id;
	}

	public void setUp_id(int up_id) {
		this.up_id = up_id;
	}

	public int getUp_user_id() {
		return up_user_id;
	}

	public void setUp_user_id(int up_user_id) {
		this.up_user_id = up_user_id;
	}
	
}

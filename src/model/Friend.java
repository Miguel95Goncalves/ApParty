package model;

public class Friend {

	private int friend_id;
	private User friend_user;
	
	public Friend() {
		super();
	}
	
	public Friend(int friend_id, User friend_user) {
		super();
		this.friend_id = friend_id;
		this.friend_user = friend_user;
	}
	
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	public User getFriend_user() {
		return friend_user;
	}
	public void setFriend_user(User friend_user) {
		this.friend_user = friend_user;
	}

}

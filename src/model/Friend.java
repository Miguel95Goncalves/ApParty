package model;

public class Friend {

	private int friend_id;
	private UserClient friend_user;
	private Status friend_status;

	public Friend(int friend_id, UserClient friend_user, Status friend_status) {
		super();
		this.friend_id = friend_id;
		this.friend_user = friend_user;
		this.friend_status = friend_status;
	}

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public UserClient getFriend_user() {
		return friend_user;
	}

	public void setFriend_user(UserClient friend_user) {
		this.friend_user = friend_user;
	}

	public Status getFriend_status() {
		return friend_status;
	}

	public void setFriend_status(Status friend_status) {
		this.friend_status = friend_status;
	}

}

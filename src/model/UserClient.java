package model;

import java.util.ArrayList;

public class UserClient extends User {
	
	private String user_nick, user_birth, user_avatar;
	private ArrayList<Friend> userArFriend; //Lista de amigos

	public UserClient(int user_id, UserType userType, String user_name, String user_email, String user_password,
			String user_contact, Status user_status, String user_nick, String user_birth, String user_avatar) {
		super(user_id, userType, user_name, user_email, user_password, user_contact, user_status);
		this.user_nick = user_nick;
		this.user_birth = user_birth;
		this.user_avatar = user_avatar;
		this.userArFriend = new ArrayList<>();
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public ArrayList<Friend> getUserArFriend() {
		return userArFriend;
	}

	public void setUserArFriend(ArrayList<Friend> arFriend) {
		this.userArFriend = arFriend;
	}

}

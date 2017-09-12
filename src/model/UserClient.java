package model;

import java.util.ArrayList;

public class UserClient extends User {

	private String user_nick, user_birth, user_avatar;
	private ArrayList<Friend> userArFriend; // Lista de amigos
	private ArrayList<Friend> userArFriendInvite; // Lista de convites de amizade
	
	public UserClient() {
		
	}

	public UserClient(int user_id, UserType userType, String user_name, String user_email,
			String user_contact, String user_nick, String user_birth, String user_avatar) {
		super(user_id, userType, user_name, user_email, user_contact);
		this.user_nick = user_nick;
		this.user_birth = user_birth;
		this.user_avatar = user_avatar;
		this.userArFriend = new ArrayList<>();
		this.userArFriendInvite = new ArrayList<>();
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

	public ArrayList<Friend> getUserArFriendInvite() {
		return userArFriendInvite;
	}

	public void setUserArFriendInvite(ArrayList<Friend> userArFriendInvite) {
		this.userArFriendInvite = userArFriendInvite;
	}

}

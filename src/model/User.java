package model;

public class User {
	
	private int user_id;
	private UserType userType;
	private String user_name, user_email, user_password, user_contact;
	private Status user_status;

	public User(int user_id, UserType userType, String user_name, String user_email, String user_password,
			String user_contact, Status user_status) {
		this.user_id = user_id;
		this.userType = userType;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_contact = user_contact;
		this.user_status = user_status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_contact() {
		return user_contact;
	}

	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}

	public Status getUser_status() {
		return user_status;
	}

	public void setUser_status(Status user_status) {
		this.user_status = user_status;
	}

}

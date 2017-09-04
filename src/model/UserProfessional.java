package model;

import java.util.ArrayList;

public class UserProfessional extends User {
	
	private ArrayList<Service> arService;

	public UserProfessional(int user_id, UserType userType, String user_name, String user_email, String user_password,
			String user_contact, Status user_status) {
		super(user_id, userType, user_name, user_email, user_password, user_contact, user_status);
		this.arService = new ArrayList<>();
	}

	public ArrayList<Service> getArService() {
		return arService;
	}

	public void setArService(ArrayList<Service> arService) {
		this.arService = arService;
	}

}

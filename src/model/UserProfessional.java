package model;

import java.util.ArrayList;

public class UserProfessional extends User {
	
	private ArrayList<Service> arService;

	public UserProfessional(int user_id, UserType userType, String user_name, String user_email,
			String user_contact) {
		super(user_id, userType, user_name, user_email, user_contact);
		this.arService = new ArrayList<>();
	}

	public ArrayList<Service> getArService() {
		return arService;
	}

	public void setArService(ArrayList<Service> arService) {
		this.arService = arService;
	}

}

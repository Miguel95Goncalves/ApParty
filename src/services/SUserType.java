package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserType;

public class SUserType implements Logic {
	
	public UserType searchUserType(int user_type_id) {
		
		for(int i=0; i<Logic.arUserType.size(); i++) {
			if(Logic.arUserType.get(i).getUser_type_id() == user_type_id) return Logic.arUserType.get(i);
		}
		
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

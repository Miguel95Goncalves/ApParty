package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Privilege;

public class SPrivilege implements Logic {

	public static Privilege searchPrivilege(int privilege_id) {
		
		for(int i=0;i < Logic.arPrivilege.size(); i++) {
			if(Logic.arPrivilege.get(i).getPrivilege_id() == privilege_id) return Logic.arPrivilege.get(i);
		}
		
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

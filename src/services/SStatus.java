package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Status;

public class SStatus implements Logic {

	public Status searchStatus(int status_id) {
		
		for(int i=0;i < Logic.arStatus.size(); i++) {
			if(Logic.arStatus.get(i).getStatus_id() == status_id) return Logic.arStatus.get(i);
		}
		
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

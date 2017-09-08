package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Service;

public class SService implements Logic  {

	public static Service searchService(int service_id) {
		for(Service s : Logic.arService) {
			if(s.getService_id() == service_id) return s;
		}
		
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

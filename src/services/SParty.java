package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Party;

public class SParty implements Logic  {

	public Party searchParty(int party_id) {
		for(Party p : Logic.arParty) {
			if(p.getParty_id() == party_id) return p;
		}
		
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

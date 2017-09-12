package services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Friend;
import model.Party;
import model.UserClient;
import model.UserParty;

public class SFriend implements Logic {

	public static void loadFriendInvites(HttpServletRequest req){ // Carregar Convites de amizade
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");
		
		for (UserClient userClient : Logic.arUserClient) {
			if (userClient.getUser_id() == user_id) {
				req.setAttribute("friendInvites", userClient.getUserArFriendInvite());
			}
		}
	}
	
	public static void loadFriendCommonParty(HttpServletRequest req) { // Carregar utilizadores presentes nas mesmas festas
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");
		
		ArrayList<Party> arUserParty = new ArrayList<>();
		ArrayList<UserClient> arUserPartyCommon = new ArrayList<>();
		
		// Guardar em "arUserParty" as festas em que o utilizador esteve presente
		for (Party party : Logic.arParty) {
			for(UserParty userParty : party.getPartyArUser()) {
				if (userParty.getUp_user_id() == user_id) {
					arUserParty.add(party);
				}
			}
		}
		ArrayList<Friend> array = (ArrayList) req.getAttribute("friendInvites");
		
		// Guardar em "arUserPartyCommon" os utilizadores presentes nas mesmas festas
		boolean check = false;
		for(Party party : arUserParty) { // Para cada festa
			for(UserParty userParty : party.getPartyArUser()) { // Para cada convidado
				for(Friend userInvite : array) { // Para cada user que enviou convite
					
				}
				
			}
		}
		
		req.setAttribute("userCommonParty", arUserPartyCommon);
	}

	public static void addFriendInvite(HttpServletRequest req) {
		
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getParameter("action").equals("loadFriendInvites")) {
			loadFriendInvites(req);
		}else if(req.getParameter("action").equals("addFriend")) {
			addFriendInvite(req);
		}
		
		return "/index.jsp";
	}

}

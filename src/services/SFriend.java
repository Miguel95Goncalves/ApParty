package services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Friend;
import model.Party;
import model.UserClient;
import model.UserParty;
import sql.UserClientSQL;

public class SFriend implements Logic {

	public static void loadFriendInvites(HttpServletRequest req) { // Carregar Convites de amizade
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		for (UserClient userClient : Logic.arUserClient) {
			if (userClient.getUser_id() == user_id) {
				req.setAttribute("friendInvites", userClient.getUserArFriendInvite());
			}
		}
	}

	public static void loadFriendCommonParty(HttpServletRequest req) { // Carregar utilizadores presentes nas mesmas
																		// festas
		HttpSession sessao = req.getSession(true);

		ArrayList<UserClient> arUserPartyCommon = UserClientSQL.loadCommonPeople((int) sessao.getAttribute("user_id"));

		req.setAttribute("userCommonParty", arUserPartyCommon);
	}

	public static ArrayList<Party> loadCommonParty(int user_id) {
		ArrayList<Party> arCommonParty = UserClientSQL.loadCommonPartys(user_id);
return arCommonParty;
		//req.setAttribute("userCommonParty", arCommonParty);
	}

	public static void addFriendInvite(HttpServletRequest req) {

	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("action").equals("loadFriendInvites")) {
			loadFriendInvites(req);
		} else if (req.getParameter("action").equals("addFriend")) {
			addFriendInvite(req);
		}

		return "/index.jsp";
	}

}

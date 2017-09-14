package services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Friend;
import model.Party;
import model.Status;
import model.UserClient;
import sql.FriendSQL;
import sql.UserClientSQL;

public class SFriend implements Logic {

	public static Friend searchFriendship(int friend_id, int user_id) { // Procurar id da relção de amizade

		UserClient uc = SUser.searchUserClient(user_id);

		for (Friend f : uc.getUserArFriendInvite()) {
			if (f.getFriend_user().getUser_id() == friend_id)
				return f;
		}

		return null;
	}

	public static void loadFriends(HttpServletRequest req) { // Carregar amigos
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		req.setAttribute("friends", SUser.searchUserClient(user_id).getUserArFriend());
	}

	public static void loadFriendInvites(HttpServletRequest req) { // Carregar Convites de amizade
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");

		req.setAttribute("friendInvites", SUser.searchUserClient(user_id).getUserArFriendInvite());

	}

	public static void loadFriendCommonParty(HttpServletRequest req) { // Carregar utilizadores presentes nas mesmas
																		// festas
		HttpSession sessao = req.getSession(true);

		ArrayList<UserClient> arUserPartyCommon = UserClientSQL.loadCommonPeople((int) sessao.getAttribute("user_id"));

		req.setAttribute("userCommonParty", arUserPartyCommon);
	}

	public static ArrayList<Party> loadCommonParty(int user_id, HttpServletRequest req) { // Carregar festas em comum
																							// com um utilizador
		ArrayList<Party> arCommonParty = UserClientSQL.loadCommonPartys(user_id, req);

		return arCommonParty;
		// req.setAttribute("userCommonParty", arCommonParty);
	}

	public static void addFriendInvite(HttpServletRequest req) { // Enviar convite de amizade
		HttpSession sessao = req.getSession(true);

		int friendId = Integer.parseInt(req.getParameter("friendId").toString());

		int userId = (int) sessao.getAttribute("user_id");
		Status friendStatus = new Status();

		for (Status s : Logic.arStatus) {
			if (s.getStatus_name().equals("Accepted") && s.getStatus_table().equals("Friend"))
				friendStatus = s;
		}

		FriendSQL.addFriend(userId, friendId);
	}

	public static void acceptFriend(HttpServletRequest req) { // Aceitar pedido de amizade
		HttpSession sessao = req.getSession(true);

		int friendId = Integer.parseInt(req.getParameter("friendId").toString());
		int userId = (int) sessao.getAttribute("user_id");

		for (int i = 0; i < SUser.searchUserClient(userId).getUserArFriendInvite().size(); i++) {

			if (SUser.searchUserClient(userId).getUserArFriendInvite().get(i).getFriend_user()
					.getUser_id() == friendId) {

				SUser.searchUserClient(userId).getUserArFriend().add(searchFriendship(friendId, userId));

				FriendSQL.acceptFriend(searchFriendship(friendId, userId).getFriend_id());

				SUser.searchUserClient(userId).getUserArFriendInvite().remove(searchFriendship(friendId, userId));
			}
		}

	}

	public static void rejectFriend(HttpServletRequest req) { // Rejeitar pedido de amizade
		HttpSession sessao = req.getSession(true);

		int friendId = Integer.parseInt(req.getParameter("friendId").toString());
		int userId = (int) sessao.getAttribute("user_id");

		for (int i = 0; i < SUser.searchUserClient(userId).getUserArFriendInvite().size(); i++) {
			if (SUser.searchUserClient(userId).getUserArFriendInvite().get(i).getFriend_user()
					.getUser_id() == friendId) {

				FriendSQL.rejectFriend(friendId);

				SUser.searchUserClient(userId).getUserArFriendInvite().remove(searchFriendship(friendId, userId));
			}
		}
	}

	public static void removeFriend(HttpServletRequest req) { // Remover amigo
		HttpSession sessao = req.getSession(true);

		int friendId = Integer.parseInt(req.getParameter("friendId").toString());
		int userId = (int) sessao.getAttribute("user_id");

		FriendSQL.removeFriend(userId, friendId);

		FriendSQL.loadUserFriends(userId);
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getParameter("action").equals("loadFriendInvites")) {
			loadFriendInvites(req);
		} else if (req.getParameter("action").equals("acceptFriend")) {
			acceptFriend(req);
		} else if (req.getParameter("action").equals("inviteFriend")) {
			addFriendInvite(req);
		} else if (req.getParameter("action").equals("rejFriend")) {
			rejectFriend(req);
		} else if (req.getParameter("action").equals("remFriend")) {
			removeFriend(req);
		}

		return "/index.jsp";
	}

}

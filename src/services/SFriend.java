package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserClient;

public class SFriend implements Logic {

	public static void loadFriendInvites(HttpServletRequest req){
		HttpSession sessao = req.getSession(true);
		int user_id = (int) sessao.getAttribute("user_id");
		
		for (UserClient userClient : Logic.arUserClient) {
			if (userClient.getUser_id() == user_id) {
				req.setAttribute("friendInvites", userClient.getUserArFriendInvite());
			}
		}
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getParameter("action").equals("loadFriendInvites")) {
			loadFriendInvites(req);
		}
		
		return "/index.jsp";
	}

}

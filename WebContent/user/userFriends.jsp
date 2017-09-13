<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.ArrayList, services.SFriend, services.SUser, model.Friend, model.UserClient, model.Party"%>
<%
	SFriend sFriend = new SFriend();
	sFriend.loadFriendInvites(request);
	sFriend.loadFriendCommonParty(request);

	HttpSession sessao = request.getSession(true);

	int loggedUser_id = (int) sessao.getAttribute("user_id");
%>
<div class="container-fluid">

	<div class="col-lg-4 text-center">
		<div class="panel-body">
			<h3>Friends</h3>
		</div>
	</div>

	<div class="col-lg-4 text-center">
		<div class="panel-body">
			<h3>Invitations</h3>
			<%
				ArrayList friendInvites = (ArrayList) request.getAttribute("friendInvites");

				out.append("<div class='panel panel-default'>" + "<div class='panel-body bg-success'>");

				for (int i = 0; i < friendInvites.size(); i++) {

					Friend friend = (Friend) friendInvites.get(i);
					String name = friend.getFriend_user().getUser_name();
					String avatar = friend.getFriend_user().getUser_avatar();
					int friendId = friend.getFriend_user().getUser_id();

					ArrayList<Party> commonPartys = SFriend.loadCommonParty(friend.getFriend_id(), request);

					if (avatar == null)
						avatar = "images/users/user";

					out.append("<div class='panel panel-default'>" + "<div class='row' Style='margin: 0.2%;'>"
							+ "<div class='col-lg-2'>" + "<img src='" + avatar + ".jpg' class='img-circle'"
							+ "alt='' width='90' height='72'>" + "</div>" + "<div class='col-lg-5'>" + "<h4>"
							+ "<a href='#'>" + name + "</a>" + "</h4>");
					for (Party p : commonPartys) {
						out.append(p.getParty_name() + ", ");
					}
					out.append("</div>" + "<div class='col-lg-5' Style='margin-top: 5%;'>");
					out.append("<div class='col-lg-6'><form method='post'>"
							+ "<input type='hidden' name='action' value='acceptFriend'/>"
							+ "<input type='hidden' name='logica' value='SFriend'/>"
							+ "<input type='hidden' name='friendId' value='" + friendId + "'/>"
							+ "<input type='submit' value='Accept' class='btn btn-default btn-sm'/></form></div>"

							+ "<div class='col-lg-6'><form method='post'>"
							+ "<input type='hidden' name='action' value='rejFriend'/>"
							+ "<input type='hidden' name='logica' value='SFriend'/>"
							+ "<input type='hidden' name='friendId' value='" + friendId + "'/>"
							+ "<input type='submit' value='Decline' class='btn btn-default btn-sm'/></form></div>");

					out.append("</div>" + "</div>" + "</div>");
				}

				out.append("</div>" + "</div>");
			%>

		</div>
	</div>
	<div class="col-lg-4 text-center">
		<!-- Pessoas que estiveram na mesma festa -->
		<div class="panel-body">
			<h3>Common Partys</h3>
			<%
				ArrayList arUserClient = (ArrayList) request.getAttribute("userCommonParty");

				out.append("<div class='panel panel-default'>" + "<div class='panel-body bg-info'>");

				for (int i = 0; i < arUserClient.size(); i++) {

					UserClient userClient = (UserClient) arUserClient.get(i);

					String name = userClient.getUser_name();
					String avatar = userClient.getUser_avatar();
					int user_id = userClient.getUser_id();
					int id = userClient.getUser_id();

					ArrayList<Party> commonPartys = SFriend.loadCommonParty(user_id, request);

					if (avatar == null)
						avatar = "images/users/user";

					if (commonPartys.size() > 0) {

						out.append("<div class='panel panel-default'>" + "<div class='row' Style='margin: 0.2%;'>"
								+ "<div class='col-lg-2'>" + "<img src='" + avatar + ".jpg' class='img-circle'"
								+ "alt='' width='90' height='72'>" + "</div>" + "<div class='col-lg-5'>" + "<h4>"
								+ "<a href='#'>" + name + "</a>" + "</h4>");
						for (Party p : commonPartys) {
							out.append(p.getParty_name() + ", ");
						}

						out.append("</div>" + "<div class='col-lg-4' Style='margin-top: 5%;'>");

						out.append("<div class='col-lg-5'><form method='post'>"
								+ "<input type='hidden' name='user_id' value='" + user_id + "'>"
								+ "<input type='hidden' name='action' value='inviteFriend'>"
								+ "<input type='hidden' name='logica' value='SFriend'>"
								+ "<input type='hidden' name='friendId' value='" + id + "'>"
								+ "<input type='submit' value='Add' class='btn btn-default btn-sm'/>" + "</form></div>"

								+ "<div class='col-lg-6'><form method='post'>"
								+ "<input type='hidden' name='action' value='remFriend'>"
								+ "<input type='hidden' name='logica' value='SFriend'>"
								+ "<input type='hidden' name='friendId' value='" + id + "'>"
								+ "<input type='submit' value='Remove' class='btn btn-default btn-sm'/>" + "</form></div>");

						out.append("</div>" + "</div>" + "</div>");
					}
				}

				out.append("</div>" + "</div>");
			%>
		</div>
	</div>
</div>
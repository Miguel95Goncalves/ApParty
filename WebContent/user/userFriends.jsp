<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.ArrayList, services.SFriend, model.Friend, model.UserClient"%>
<%
	SFriend sFriend = new SFriend();
	sFriend.loadFriendInvites(request);
	sFriend.loadFriendCommonParty(request);
%>
<div class="container">
	<div class="row">
		<h3>Invitations</h3>
		<div class="panel-body">
			<%
				// Convites de amizade
				ArrayList FriendInvites = (ArrayList) request.getAttribute("friendInvites");

				out.append("<div class='panel panel-default'>" + "<div class='panel-body bg-success'>");

				for (int i = 0; i < FriendInvites.size(); i++) {

					Friend friend = (Friend) FriendInvites.get(i);

					String name = friend.getFriend_user().getUser_name();
					String avatar = friend.getFriend_user().getUser_avatar();

					if (avatar == null)
						avatar = "images/users/user";

					out.append("<div class='panel panel-default'>" + "<div class='row text-center' Style='margin: 1.5%;'>"
							+ "<div class='col-lg-2'>" + "<img src='" + avatar + ".jpg' class='img-circle'"
							+ "alt='' width='120' height='80'>" + "</div>" + "<div class='col-lg-6'>" + "<h2>"
							+ "<a href='#'>" + name + "</a>" + "</h2>" + "</div>"
							+ "<div class='col-lg-4' Style='margin-top: 1.5%;'>"
							+ "<button class='btn btn-default btn-lg'>Accept</button>"
							+ "<button class='btn btn-default btn-lg'>Decline</button>" + "</div>" + "</div>" + "</div>");
				}

				out.append("</div>" + "</div>");
			%>

		</div>
	</div>
	<div class="row">
		<!-- Pessoas que estiveram na mesma festa -->
		<h3>Common Partys</h3>
		<div class="panel-body">
			<%
				// Users com festas em comum
				ArrayList arUserClient = (ArrayList) request.getAttribute("userCommonParty");

				out.append("<div class='panel panel-default'>" + "<div class='panel-body bg-primary'>");

				for (int i = 0; i < arUserClient.size(); i++) {

					UserClient userClient = (UserClient) arUserClient.get(i);

					String name = userClient.getUser_name();
					String avatar = userClient.getUser_avatar();
					int user_id = userClient.getUser_id();

					if (avatar == null)
						avatar = "images/users/user";

					out.append("<div class='panel panel-default'>" + "<div class='row text-center' Style='margin: 1.5%;'>"
							+ "<div class='col-lg-2'>" + "<img src='" + avatar + ".jpg' class='img-circle'"
							+ "alt='' width='120' height='80'>" + "</div>" + "<div class='col-lg-6'>" + "<h2>"
							+ "<a href='#'>" + name + "</a>" + "</h2>" + "</div>"
							+ "<div class='col-lg-4' Style='margin-top: 1.5%;'>"
							+ "<form method='post'>"
							+ "<input type='hidden' name='user_id' value='" + user_id + "'>"
							+ "<input type='hidden' name='action' value='addFriend'>"
							+ "<input type='hidden' name='logica' value='SFriend'>"
							+ "<input type='submit' value='Add' class='btn btn-default btn-lg'/>"
							+ "</form>"
							+ "<input type='submit' value='Remove' class='btn btn-default btn-lg'/>" + "</div>" + "</div>" + "</div>");
				}

				out.append("</div>" + "</div>");
			%>
		</div>
	</div>
</div>
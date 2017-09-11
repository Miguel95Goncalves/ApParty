<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, services.SFriend, model.Friend"%>
<%
	SFriend sFriend = new SFriend();
	sFriend.loadFriendInvites(request);
%>
<div class="container">
	<div class="row">
		<!-- Convites de amizade -->
		<h3>Invitations</h3>

		<%
			ArrayList FriendInvites = (ArrayList) request.getAttribute("friendInvites");

			out.append("<div class='panel panel-default'>" + "<div class='panel-body bg-success'>");

			for (int i = 0; i < FriendInvites.size(); i++) {
				
				Friend friend = (Friend) FriendInvites.get(i);
				
				String name = friend.getFriend_user().getUser_name();
				String avatar = friend.getFriend_user().getUser_avatar();
				
				if(avatar == null) avatar = "images/users/user";
				
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

		<!--<div class="panel panel-default">
			<div class="panel-body bg-success">
				<div class="panel panel-default">
					<div class="row text-center" Style="margin: 1.5%;">
						<div class="col-lg-2">
							<img src="images/services/s1.jpg" class="img-circle"
								alt="Cinque Terre" width="120" height="80">
						</div>
						<div class="col-lg-6">
							<h2>
								<a href="#">Nome do Gajo</a>
							</h2>
						</div>
						<div class="col-lg-4" Style="margin-top: 1.5%;">
							<button class="btn btn-default btn-lg">Accept</button>
							<button class="btn btn-default btn-lg">Decline</button>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="row text-center" Style="margin: 15px;">
						<div class="col-lg-2">
							<img src="images/services/s1.jpg" class="img-circle"
								alt="Cinque Terre" width="120" height="80">
						</div>
						<div class="col-lg-6">
							<h2>
								<a href="#">Nome do Gajo</a>
							</h2>
						</div>
						<div class="col-lg-4" Style="margin-top: 1.5%;">
							<button class="btn btn-default btn-lg">Accept</button>
							<button class="btn btn-default btn-lg">Decline</button>
						</div>
					</div>
				</div>
			</div>
		</div>-->
	</div>
	<div class="row">
		<!-- Pessoas que estiveram na mesma festa -->
		<h3>Common Partys</h3>
		<div class="panel panel-default">
			<div class="panel-body">body</div>
		</div>
	</div>
</div>
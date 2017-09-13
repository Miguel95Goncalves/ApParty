<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.ArrayList, services.SUser"%>
	<!-- user_id=1-Client
		 user_id=2-Profissional
		 user_id= -->
	
<% 
HttpSession sessao = request.getSession(true);
if(session.getAttribute("user_id") == null){
	%><nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp"><img class="img-responsive" src="./imgs/imgAA.gif" style="width:60px;height:30px;"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#modalLogin">Login</button>
				</li>
			</ul>
		</div>
	</div>
</nav><% 
}else{
	System.out.println(session.getAttribute("user_user_type_id"));
	if((int)session.getAttribute("user_user_type_id")== 1){
		%><nav class="navbar navbar-default navbar-static-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"><img class="img-responsive" src="./imgs/imgAA.gif" style="width:60px;height:30px;"></a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li id="home" class="active"><a href="index.jsp">Home</a></li>
					<li id="users" class="active"><a href="index?pag=users">Users</a></li>
					<li id="friend" class="active"><a href="index?pag=friends">Friends</a></li>
					<!--<li id="service" class="active"><a href="index?pag=services">Services</a></li>-->
					<li id="party" class="active"><a href="index?pag=parts">Partys</a></li>
					<li id="party" class="active"><a href="index?pag=partss">FriendRequest</a></li>
					<li id="acept" class="active"><a href="index?pag=acept">Acept Party</a></li>
					<li id="acepts" class="active"><a href="index?pag=acepts">Detail Service</a></li>
					<li id="profile" class="active"><a href="index?pag=profile">Profile</a></li>
					<li id="sair" class="active"><a href="index?pag=logout">Logout</a></li>
				</ul>
			</div>
		<!-- </div> -->
		</div>
	</nav><%
	}else if((int) session.getAttribute("user_user_type_id") == 2){ 
%><nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp"><img class="img-responsive" src="./imgs/imgAA.gif" style="width:60px;height:30px;"></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li id="home" class="active"><a href="index.jsp">Home</a></li>
				<li id="users" class="active"><a href="index?pag=users">Users</a></li>
				<!-- <li id="friend" class="active"><a href="index?pag=friends">Friends</a></li>-->
				<!--<li id="service" class="active"><a href="index?pag=services">Services</a></li>-->
				<!-- <li id="party" class="active"><a href="index?pag=parts">Partys</a></li>-->
				<!-- <li id="party" class="active"><a href="index?pag=partss">FriendRequest</a></li>-->
				<!-- <li id="acept" class="active"><a href="index?pag=acept">Acept Party</a></li>-->
				<li id="acepts" class="active"><a href="index?pag=acepts">Detail Service</a></li>
				<li id="profile" class="active"><a href="index?pag=profile">Profile</a></li>
				<li id="sair" class="active"><a href="index?pag=logout">Logout</a></li>
			</ul>
		</div>
	<!-- </div> -->
	</div>
</nav><%
}
	}
%>
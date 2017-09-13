<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="services.SUser"%>

<main>
	<%
		if (request.getParameter("pag") != null) {
			if (request.getParameter("pag").equals("users")||request.getParameter("pag").equals("profile")) {
				%> <jsp:include page="../user/userProfile.jsp" /> <%
			} else if (request.getParameter("pag").equals("friends")) {
				%> <jsp:include page="../user/userFriends.jsp" /> <%
			} else if (request.getParameter("pag").equals("services")) {
				%> <jsp:include page="../service/serviceFilter
				.jsp" /><% 
			} else if (request.getParameter("pag").equals("posts")){
				%> <jsp:include page="../post/post.jsp" /><% 
			} else if (request.getParameter("pag").equals("parts")){
				%> <jsp:include page="../party/party.jsp" /><% 
			} else if (request.getParameter("pag").equals("partss")){
				%> <jsp:include page="../party/partyII.jsp" /><% 
			} else if(request.getParameter("pag").equals("acept")){
				%> <jsp:include page="../party/partyIII.jsp" /><%
			} else if(request.getParameter("pag").equals("acepts")){
				%> <jsp:include page="../party/partyIV.jsp" /><%
			} else if(request.getParameter("pag").equals("ufriends")){
				%> <jsp:include page="../user/userFriends.jsp" /> <%
			}else if(request.getParameter("pag").equals("logout")){
				SUser.logout(request);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	%>
</main>


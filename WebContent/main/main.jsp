<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<main>
	<%
		if(request.getParameter("pag").equals("friends")){
			%> <jsp:include page="/user/userFriends.jsp" /> <%
		}
	%>

</main>
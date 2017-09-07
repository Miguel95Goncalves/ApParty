<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<main> <%
 	if (request.getParameter("pag") != null) {
 		System.out.append("cu");
 		if (request.getParameter("pag").equals("service")) {
 %> <jsp:include page="../service/service.jsp" /> <%
 	} 
 	}else {
 %> <jsp:include page="../service/serviceFilter.jsp" /> <%
 	}
 %> </main>
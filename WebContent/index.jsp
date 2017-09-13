<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<title>ApParty</title>
</head>
<body>
	<jsp:include page="/main/nav.jsp" />
	<%
	 if(request.getSession().getAttribute("user_id")==null){
		 %> <jsp:include page="/home.jsp" /> <%
	 }else if(request.getParameter("pag")!=null){
	 		%> <jsp:include page="/main/main.jsp" /><%
	 }else{
		 if((int)request.getSession().getAttribute("user_user_type_id") == 3){
			 %>
			  <div class="col-xs-12">
				 <figure class="thumbnail">
	  				<a href="index?pag=logout"><img src="./imgs/images.png" alt="" class="img-responsive center-block"></a>
				</figure> 
			 </div>
			 <div class="col-xs-12">
				 <figure class="thumbnail">
	  				<img src="./imgs/imgAA.gif" alt="Minha Figura" class="img-responsive center-block">	
				</figure> 
			 </div>
			 
			<%
		 }else if((int)request.getSession().getAttribute("user_user_type_id")<=2){
			 %>
			  <div class="col-xs-12">
				 <figure class="thumbnail">
	  				<img src="./imgs/images.png" alt="" class="img-responsive center-block">
				</figure> 
			 </div>
			 <div class="col-xs-12">
				 <figure class="thumbnail">
	  				<img src="./imgs/imgAA.gif" alt="Minha Figura" class="img-responsive center-block">	
				</figure> 
			 </div>
			 
			<%
		 }
		
		 
	 }
	%>
	<jsp:include page="/main/login.jsp" />
	<footer class="footer text-center navbar-fixed-bottom">
		<div>
			<p class="text-muted class="text-justify">
			End-of-Course Project Technician / Specialist Course in Technologies and Information Systems Programming</p>
		</div>
	</footer>
</body>
</html>
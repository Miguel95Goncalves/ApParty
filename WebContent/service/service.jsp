<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="container-fluid">

	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading"><h2> <% out.append(request.getAttribute("service_name").toString()); %></h2></div>
			<div class="panel-body">Panel Content</div>
		</div>
	</div>
</div>
<%@page import="model.Service"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, model.User, services.SService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container-fluid">
	<div class="col-lg-12">
		<div class="col-lg-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<center>
						<h3>Filter</h3>
					</center>
				</div>
				<div class="panel-body">
					<select><option>something</option></select>
				</div>
			</div>
		</div>
		<div class="col-lg-10">

			<%
				ArrayList serviceList = (ArrayList) request.getAttribute("serviceList");
				int cont = 1;

				for (int i = 0; i < serviceList.size(); i++) {

					Service service = (Service) serviceList.get(i);
					
					if (cont == 1)
						out.append("<div class='row'>");

					out.append("<div class='col-lg-4'>" + "<a href='index?pag=service'>" + "<div class='thumbnail'>" + "<img class='img-thumbnail' src='" + service.getService_images()
							+ "'>" + "<div class='caption'>" + "<h3>" + service.getService_name() + " size: " + serviceList.size() + "</h3>" + "<p>" + service.getService_tiny_description() + "</p>" + "</div>"
							+ "</div>" + "</a>" + "</div>");

					if (cont == 3) {
						out.append("</div>");
						cont = 0;
					}

					cont++;
				}
				
				if(cont != 1) out.append("</div>");
			%>

		</div>
	</div>
</div>
</div>
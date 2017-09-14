<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="model.Service, services.Logic, java.util.ArrayList, model.UserClient"%>


 <div id="choise">
    <%
    	ArrayList<Service> s = Logic.arService;
    
    	for(int i=0; i<s.size(); i++){
    		Service service = (Service) s.get(i);
    		out.append("<div>"
    					+ "<form action='index?pag=services' method='post'>"
    					+ "<input type='hidden' name='logica' id='logica' value='SService'>"
    					+ "<input type='hidden' name='action' id='action' value='infoService'>"
    					+ "<input type='hidden' id='service_id' name='service_id'>"
    					+ "<input class='btn btn-warning' type='submit' id='btnService' name='btnService' style='display: none;'>"
    					+ "<a id='" + service.getService_id() + "' class='choiseService'>"+service.getService_name() +"</a>"
    					+ "</form>"	
    					+ "</div>");
    	}
    %>

</div>


<%
Service service = (Service) request.getAttribute("infoObjSercive");
UserClient u = (UserClient) request.getAttribute("infoOwnerService");

if(service != null && u != null){
	out.append("<div id='infoDiv' class='col-lg-12' style='display: none;'>"
			+		"<div class='panel panel-default'>"
			+			"<div class='panel panel-heading'>"
			+				"<h4>"+service.getService_name()+"</h4>"
			+			"</div>"
			+			"<div class='panel panel-body'>"
			+				"<div class='col-lg-3'>"
			+					"<img src='http://lucioping.altervista.org/Campioni/Campioni_waldner2_large.jpg' style='width: 300px; heigth: 300px;'>"
			+ 				"</div>"
			+				"<div class='container-fluid'>"
			+					"<div class='col-lg-9'>"
			+						"<div class='container-fluid'>"
			+ 							"<div class='col-lg-6'>"
			+								"<div class='col-lg-6'>"
			+									"<h4>Name Business:</h4>"
			+								"</div>"
			+								"<div class='col-lg-6'>"
			+									"<h4 class='info_params_label'>"+service.getService_name()+"</h4>"
			+								"</div>"
			+								"<div class='col-lg-6'>"
			+									"<h4>Category:</h4>"
			+								"</div>"
			+								"<div class='col-lg-6'>"
			+									"<h4 class='info_params_label'>"+service.getService_category().getCategory_name()+"</h4>"
			+								"</div>"
			+								"<div class='col-lg-6'>"
			+									"<h4>Tiny Description:</h4>"
			+								"</div>"
			+								"<div class='col-lg-6'>"
			+									"<h4 class='info_params_label'>"+service.getService_tiny_description()+"</h4>"
			+								"</div>"
			+ 								"<div class='col-lg-6'>"
			+									"<h4>Full Description:</h4>"
			+								"</div>"
			+								"<div class='col-lg-6'>"
			+									"<h4 class='info_params_label'>"+service.getService_full_description()+"</h4>"
			+								"</div>"
			+							"</div>"
			+  						"<div class='col-lg-6'>"
			+							"<div class='col-lg-6'>"
			+								"<h4>Provider Contact:</h4>"
			+							"</div>"
			+							"<div class='col-lg-6'>"
			+								"<h4 class='info_params_label'>"+u.getUser_name()+"</h4>"
			+							"</div>"
			+							"<div class='col-lg-6'>"
			+								"<h4>Mobile:</h4>"
			+							"</div>"
			+							"<div class='col-lg-6'>"
			+								"<h4 class='info_params_label'>"+u.getUser_contact()+"</h4>"
			+							"</div>"
			+							"<div class='col-lg-6'>"
			+								"<h4>Email:</h4>"
			+							"</div>"
			+							"<div class='col-lg-6'>"
			+								"<h4 class='info_params_label'>"+u.getUser_email()+"</h4>"
			+							"</div>"
			+						"</div>"
			+						"</div>"
			+					"</div>"
			+				"</div>"
			+		"</div>"
			+ "</div>");
}
%>


<input type="hidden" id="activeInfo" value='<%=request.getAttribute("infoService")%>'>


<style>

.info_params_label {
    color: #0071ce;
}

.info_params_label:focus, .info_params_label:hover{
	color: #00a0e6;
}

</style>

<script>
$(document).ready(function() {
	var x = $("#activeInfo").val();
	
	if(x == 1){
		$("#infoDiv").css("display", "block");
		$("#choise").css("display", "none");
	}else{
		
	}
});

$(".choiseService").on("click", function() {
	$("#service_id").val($(this).attr("id"));
	$("#btnService").click();
});
    
</script>
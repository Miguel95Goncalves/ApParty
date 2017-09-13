<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList, model.Party, model.Post, services.Logic, services.SParty"%>

<div class="container-fluid">
  <div class="row content">
  <div class="col-sm-12">
  	<div class="col-sm-2 panel panel-default text-center alert alert-secondary">
  		<%
  			//request.setAttribute("arrayPartys", Logic.arParty);
			ArrayList arParty = (ArrayList) request.getAttribute("arrayPartys");
				if (arParty != null) {
					for (int i = 0; i < arParty.size(); i++) {
						Party pt = (Party) arParty.get(i);
						System.out.println(pt.getParty_name());
						out.append("<div><a href='#'>"+pt.getParty_name()+"</a></div>");
					}
				}else{
					System.out.println("nullo");
				}
		%>
		
		<label class="text-muted"><h4>EVENTS</h4></label>
		
  	</div>
  	<div class="col-sm-8 text-center panel panel-default alert alert-secondary">
  		<%
  			//request.setAttribute("arrayParts", Logic.arPost);
  			ArrayList arPost = (ArrayList) request.getAttribute("arrayPost");
  			if( arPost != null){
  				for(int i = 0; i < arPost.size(); i++){
  					Post post = (Post) arPost.get(i);
  					out.append("");
  				}
  			}
  		%>
  		<label class="text-muted"><h4>POSTS</h4></label>
		
  	</div>
  	<div class="col-sm-2 panel panel-default text-center alert alert-secondary">
  		<label class="text-muted"><h4>PUBLICITY</h4></label>
  	</div>
  
  </div>
  	
  </div>    
</div>
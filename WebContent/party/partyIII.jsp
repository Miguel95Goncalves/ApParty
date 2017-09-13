<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList, model.Party, model.User, model.Friend, model.PartyInvite, model.UserClient"%>


<div class="container-fluid">
	<h4 class="text-muted"><i>PARTY INVITATIONS</i></h4>
	<div class="row content">
		<div class="col-sm-12  panel panel-default alert alert-secondary">
			<div class="col-sm-8 ">
				<%
					ArrayList arPartys = (ArrayList) request.getAttribute("arrayPartys");
					arPartys.isEmpty();
					if(arPartys!=null){
						for(int partys = 0; partys<arPartys.size();partys++){
							Party pt = (Party) arPartys.get(partys);
							out.append("<div>+"+pt.getParty_name()+"+</div>");
							for(int partysInv = 0; partysInv < pt.getPartyArPartyInvite().size(); partysInv++){
								PartyInvite pi = (PartyInvite) pt.getPartyArPartyInvite().get(partysInv);
								ArrayList arUsers = (ArrayList) request.getAttribute("arrayUserClients");
								for(int u=0; u<arUsers.size(); u++){
									UserClient uCli = (UserClient) arUsers.get(u);
									if(pi.getPi_user_invited().getUser_id() == uCli.getUser_id()){
										
										out.append("<div class='col-sm-1'>"
					            				+"<img class='img-responsive img-circle' src='"+ uCli.getUser_avatar()+"' />"
					           		 		+"</div>"
					           		 		+"<div class='col-sm-offset-1 col-sm-4 text-center'>"
					           		 			+"<h5 class='text-muted'><i>'"+uCli.getUser_name()+"'</i></h5>"
					           		 		+"</div>"
					    					+"</div>"
					    						+"<div class='col-sm-4 text-center'>"
					    							+"<div class='row justify-content-center align-items-center'>"
					    								+"<button type='button' class='btn btn-outline  text-muted' id='party_going' name='party_going'><i>I will</i></button>"
					    								+"<button type='button' class='btn btn-outline  text-muted' id='party_mabe' name='party_mabe'><i>Mabe i'm go</i></button>"
					    								+"<button type='button' class='btn btn-outline  text-muted' id='party_not_going' name='party_not_going'><i>I'm not going</i></button>"
					    						+"</div>"
					    						+"</div>");
									}
								}
								
							
							}	
						}
					}
				
				%>
				
				<!-- <div class="col-sm-1">
            		<img class="img-responsive img-circle" src="http://placekitten.com/g/200/200" />
       		 	</div>
       		 	<div class="col-sm-offset-1 col-sm-4 text-center">
       		 		<h5 class="text-muted"><i>Fabricio Harmonio Joaquim</i></h5>
       		 	</div>
			</div>
			<div class="col-sm-4 text-center">
				<div class="row justify-content-center align-items-center">
					<button type="button" class="btn btn-outline  text-muted" id="party_going" name="party_going" ><i>I will</i></button>
					<button type="button" class="btn btn-outline  text-muted" id="party_mabe" name="party_mabe"><i>Mabe i'm go</i></button>
					<button type="button" class="btn btn-outline  text-muted" id="party_not_going" name="party_not_going"><i>I'm not going</i></button>
				</div>
			</div>
		</div>-->
	</div>
</div>

<div class="container-fluid">
	<h4 class="text-muted"><i>PUBLIC PARTIES</i></h4>
	<div class="row content">
		<div class="col-sm-12  panel panel-default alert alert-secondary">
			<div class="col-sm-8 ">
				<div class="col-sm-1">
            		<img class="img-responsive img-circle" src="http://placekitten.com/g/200/200" />
       		 	</div>
       		 	<div class="col-sm-offset-1 col-sm-4 text-center">
       		 		<h5 class="text-muted"><i>Fabricio Harmonio Joaquim</i></h5>
       		 	</div>
			</div>
			<div class="col-sm-4 text-center">
				<div class="row justify-content-center align-items-center">
					<button type="button" class="btn btn-outline text-muted" id="invitation_accept" name="invitation_accept"><i>Accept</i></button>
					<button type="button" class="btn btn-outline text-muted" id="invitation_decline" name="invitation_decline"><i>Decline</i></button>
				</div>
			</div>
		</div>
	</div>
</div>



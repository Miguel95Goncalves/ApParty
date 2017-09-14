<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="services.SUser"%>
    <% SUser sUser = new SUser();%>
	<% sUser.loadClientInformation(request);%>
 <div class="tabbable"> <!-- Only required for left/right tabs -->
	      <ul class="nav nav-tabs">
	        <li class="active" id="settingsProfile" ><a>Settings</a></li>
	        <li class="active" id="settingsProfile2" role="presentation" style="display: none;"><a data-toggle="tab">Settings</a></li>
	        <li id="listLogout"><a data-toggle="tab">Logout</a></li>
	      </ul>
	      <div class="tab-content">
	        <div class="tab-pane active" id="tab1">
	          <div id="formUserSettings" style="display:none; margin-left: 25%;"><!-- AQUI DEVE CONTEM A INFORMACAO DA RESPETIVA PESSOA -->
				<form method="POST" action="index" class="form-horizontal">
					<div class="panel panel-success">
						<div class="panel-heading">Settings User</div>
							<div class="panel-body">
								<div id="alertPassword" class="alert alert-danger" role="alert" style="display: none;">
										<strong>Ups!</strong> Email or Password are not valid
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">Name: </label>
										<div class="col-sm-8">
												<div class="nameDiv">
													<input type="text" class="form-control" id="settings_name_new" name="settings_name_new" disabled="true" value='<%=request.getAttribute("user_name")%>'/>
													<a class="glyphicon glyphicon-pencil" id="editName">Edit</a>	
												</div>
										</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-sm-2">Nick:</label>
										<div class="col-sm-8">
											<div class="nickDiv">
												<input type="text" class="form-control" id="settings_nick_new" name="settings_nick_new" pattern="[A-Z a-z] {3}" disabled="true" value='<%=request.getAttribute("user_nick")%>'/>
												<a class="glyphicon glyphicon-pencil" id="editNick">Edit</a>	
											</div>
										</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-sm-2">Email:</label>
										<div class="col-sm-8">
											<div class="emailDiv">
												<input type="email" id="settings_email_new" name="settings_email_new"  class="form-control"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" disabled="true" value='<%=request.getAttribute("user_email")%>'/>
												<a class="glyphicon glyphicon-pencil" id="editEmail">Edit</a>
											</div>
										</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-sm-2">Contact: </label>
										<div class="col-sm-8">
											<div class="contactDiv">
												<input type="text" id="settings_contact_new" name="settings_contact_new"  class="form-control" pattern="[0-9] [9-13]{9,13}" disabled="true" value='<%=request.getAttribute("user_contact")%>'/>
												<a class="glyphicon glyphicon-pencil" id="editContact">Edit</a>
											</div>
										</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-sm-2">Birhtday: </label>
										<div class="col-sm-8">
											<div class="birthdayDiv">
												<input type="date" id="settings_birthday_new" name="settings_birthday_new"  class="form-control" disabled="true" value='<%=request.getAttribute("user_birth")%>'/>
												<a class="glyphicon glyphicon-pencil" id="editBirthday">Edit</a>
											</div>
										</div>
								</div>
					
								<div class="form-group">
									<label class="control-label col-sm-2" id="lb_pass"> Password: </label>
										<div class="col-sm-8">
											<div class="passwordDiv">
												<input type="password" class="form-control" id="settings_password_actual" name="settings_password_actual" disabled="true" style="display: block;" value='<%=request.getAttribute("user_pass")%>'/>
												<input type="password" class="form-control" id="settings_old_password" name="settings_old_password" style="display: none;" disabled="false"  oninput="changePass();" Placeholder="Write the old password"/>
												<a class="glyphicon glyphicon-pencil" id="editPass">Edit</a>
											</div>
										</div>
								</div>
								
								<div class="form-group" id="divPasswordNew" style="display: none;">
									<label class="control-label col-sm-2" id="lb_pass_new" name="lb_pass_new">New Password: </label>
										<div class="col-sm-8">
											<div class="newPasswordDiv">
												<input type="password" class="form-control" id="settings_new_password" name="settings_new_password" Placeholder="Write the new password"/>
												<a class="glyphicon glyphicon-pencil" id="editNewPass" style="color: white">Edit</a>
											</div>
										</div>
								</div>
								
								<div class="form-group"  id="divConfirmPassword" style="display: none;">
									<label class="control-label col-sm-2" id="lb_pass_new_confirm" name="lb_pass_new_confirm">Confirm Password: </label>
										<div class="col-sm-8">
											<div class="newPasswordDiv">
												<input type="password" class="form-control" id="settings_new_password_confirm" name="settings_new_password_confirm" oninput="changeConfirmPassword();" Placeholder="Confirm the password"/>
												<a class="glyphicon glyphicon-ok" id="editConfirmPassSuccess" style="color: green; display:none;" ></a>
												<a class="glyphicon glyphicon-remove" id="editConfirmPassError" style="color: red; display:none;" ></a>
												<a class="glyphicon glyphicon-pencil" id="editConfirmEdit" style="color: white; display:block;" >Edit</a>
											</div>
										</div>
								</div>
								<div style="margin-left: 531px;">
									<input type="submit" class="btn btn-success" value="Save" /> 
									<input type="hidden" id="settings_user_id" name="settings_user_id" value='<%=request.getAttribute("user_id")%>'/>
									<input type="hidden" name="logica" id="logica" value="SUser">
									<input type="hidden" name="action" id="action" value="changeSettings">
								</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>  
</div> 
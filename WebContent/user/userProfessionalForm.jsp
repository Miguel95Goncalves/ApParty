<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="services.SUser, services.SService, services.SCategory ,java.util.ArrayList, model.Service, model.Category, services.Logic"%>
<%
	SUser sUser = new SUser();
%>
<%
	SCategory category = new SCategory();
%>
<%
	sUser.loadClientInformation(request);
%>
<%
	category.loadCategoryInformation(request);
%>

<button id="btnOpenAddService" name="btnOpenAddService"
	style="display: none;" type="button" data-toggle="modal"
	data-target="#openAddService"></button>
<button id="btnOpenEditService" name="btnOpenEditService"
	style="display: none;" type="button" data-toggle="modal"
	data-target="#openEditServiceModal"></button>
<input type="hidden" id="editServiceModal" name="editServiceModal"
	value='<%=request.getAttribute("editService")%>'>

<div class="tabbable">
	<ul class="nav nav-tabs">
		<li class="active" id="settingsProfile"><a>Settings</a></li>
		<li class="active" id="settingsProfile2" role="presentation"
			style="display: none;"><a data-toggle="tab">Settings</a></li>
		<li id="settingsServices"><a>Services</a></li>
		<li id="settingsServices2" style="display: none;"><a>Services</a></li>
		<li id="listLogout"><a data-toggle="tab">Logout</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="tab1">
			<div id="formUserSettings" style="display: none; margin-left: 25%;">
				<!-- AQUI DEVE CONTEM A INFORMACAO DA RESPETIVA PESSOA -->
				<form method="POST" action="index" class="form-horizontal">
					<div class="panel panel-success">
						<div class="panel-heading">Settings User</div>
						<div class="panel-body">
							<div class="form-group">
								<label class="control-label col-sm-2">Name Professional:
								</label>
								<div class="col-sm-8">
									<div class="nameDiv">
										<input type="text" class="form-control" id="settings_name_new"
											name="settings_name_new" disabled="true"
											value='<%=request.getAttribute("user_name")%>' /> <a
											class="glyphicon glyphicon-pencil" id="editName">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2">Nick:</label>
								<div class="col-sm-8">
									<div class="nickDiv">
										<input type="text" class="form-control" id="settings_nick_new"
											name="settings_nick_new" pattern="[A-Z a-z] {3}"
											disabled="true"
											value='<%=request.getAttribute("user_nick")%>' /> <a
											class="glyphicon glyphicon-pencil" id="editNick">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2">Email:</label>
								<div class="col-sm-8">
									<div class="emailDiv">
										<input type="email" id="settings_email_new"
											name="settings_email_new" class="form-control"
											pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
											disabled="true"
											value='<%=request.getAttribute("user_email")%>' /> <a
											class="glyphicon glyphicon-pencil" id="editEmail">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2">Contact: </label>
								<div class="col-sm-8">
									<div class="contactDiv">
										<input type="text" id="settings_contact_new"
											name="settings_contact_new" class="form-control"
											pattern="[0-9] [9-13]{9,13}" disabled="true"
											value='<%=request.getAttribute("user_contact")%>' /> <a
											class="glyphicon glyphicon-pencil" id="editContact">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2">Birhtday: </label>
								<div class="col-sm-8">
									<div class="birthdayDiv">
										<input type="date" id="settings_birthday_new"
											name="settings_birthday_new" class="form-control"
											disabled="true"
											value='<%=request.getAttribute("user_birth")%>' /> <a
											class="glyphicon glyphicon-pencil" id="editBirthday">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-2" id="lb_pass">
									Password: </label>
								<div class="col-sm-8">
									<div class="passwordDiv">
										<input type="password" class="form-control"
											id="settings_password_actual" name="settings_password_actual"
											disabled="true" style="display: block;"
											value='<%=request.getAttribute("user_pass")%>' /> <input
											type="password" class="form-control"
											id="settings_old_password" name="settings_old_password"
											style="display: none;" disabled="false"
											oninput="changePass();" Placeholder="Write the old password" />
										<a class="glyphicon glyphicon-pencil" id="editPass">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group" id="divPasswordNew"
								style="display: none;">
								<label class="control-label col-sm-2" id="lb_pass_new"
									name="lb_pass_new">New Password: </label>
								<div class="col-sm-8">
									<div class="newPasswordDiv">
										<input type="password" class="form-control"
											id="settings_new_password" name="settings_new_password"
											Placeholder="Write the new password" /> <a
											class="glyphicon glyphicon-pencil" id="editNewPass"
											style="color: white">Edit</a>
									</div>
								</div>
							</div>

							<div class="form-group" id="divConfirmPassword"
								style="display: none;">
								<label class="control-label col-sm-2" id="lb_pass_new_confirm"
									name="lb_pass_new_confirm">Confirm Password: </label>
								<div class="col-sm-8">
									<div class="newPasswordDiv">
										<input type="password" class="form-control"
											id="settings_new_password_confirm"
											name="settings_new_password_confirm"
											oninput="changeConfirmPassword();"
											Placeholder="Confirm the password" /> <a
											class="glyphicon glyphicon-ok" id="editConfirmPassSuccess"
											style="color: green; display: none;"></a> <a
											class="glyphicon glyphicon-remove" id="editConfirmPassError"
											style="color: red; display: none;"></a> <a
											class="glyphicon glyphicon-pencil" id="editConfirmEdit"
											style="color: white; display: block;">Edit</a>
									</div>
								</div>
							</div>
							<div style="margin-left: 531px;">
								<input type="submit" class="btn btn-success" value="Save" /> <input
									type="hidden" id="settings_user_id" name="settings_user_id"
									value='<%=request.getAttribute("user_id")%>' /> <input
									type="hidden" name="logica" id="logica" value="SUser">
								<input type="hidden" name="action" id="action"
									value="changeSettings">
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- Acaba a div da settings do user -->
		</div>
	</div>
	<!-- Aqui acaba a main div -->


	<!-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
	<div class="tab-content"> <!-- Tabela de serviços -->
		<div class="tab-pane active">
			<div class="col-sm-8">
				<div class="panel panel-success" style="display: none;"
					id="tableUserService" name="tableUserService">
					<div class="panel-heading">Settings User</div>
					<div class="table-responsive">
						<h4>
							<a id="addService" name="addService"><i
								class="glyphicon glyphicon-plus"></i> Add Service</a>
						</h4>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Company Name</th>
									<th>Service</th>
									<th>Tiny Description</th>
									<th>Full Description</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
									ArrayList<Service> s = new ArrayList<Service>();
									s.addAll(sUser.loadServicesUser(request));
									if (s != null) {
										for (int i = 0; i < s.size(); i++) {
											Service service = (Service) s.get(i);
											out.append("<tr><td>" + service.getService_name() + "</td>" + "<td>"
													+ service.getService_category().getCategory_name() + "</td>" + "<td>"
													+ service.getService_tiny_description() + "</td>" + "<td>"
													+ service.getService_full_description() + "</td>"

													+ "<td><form action='index?pag=users' method='POST'>"
													+ "<input type='hidden' name='logica' id='logica' value='SUser'>"
													+ "<input type='hidden' name='action' id='action' value='sendServices'>"
													+ "<input type='hidden' id='name_service_id' name='user_service_id'>"
													+ "<input class='btn btn-warning' type='submit' id='btnEditService' name='btnEditService' style='display: none;' value='Editar' name='edit'>"
													+ "<a id='" + service.getService_id()
													+ "' class='editService'><i class='glyphicon glyphicon-pencil'></i></a>" + "</form></td>"

													+ "<td><form action='index?pag=users' method='POST'>"
													+ "<input type='hidden' name='logica' value='SUser'>"
													+ "<input type='hidden' name='action' value='deleteService'>"
													+ "<input type='hidden' name='user_service_id_delete' id='user_service_id_delete'>"
													+ "<input class='btn btn-danger' id='btnDeleteService' name='btnDeleteService' style='display: none;' type='submit' value='Delete' name='Elim'>"
													+ "<a class='deleteService' id='"+ service.getService_id() +"'><i class='glyphicon glyphicon-remove'></i></a>"
													+ "</form></td></tr>");
										}
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<div id="openAddService" class="modal fade" role="dialog">
	<!-- Modal de Adicionar -->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" id="modal-header">
				<button type="button" id="closeLogin" class="close"
					data-dismiss="modal">&times;</button>
				<h4 class="modal-title" id="headerLogin">Add Service</h4>
			</div>
			<form method="POST" action="index" class="form-horizontal">
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label col-sm-2">Name Business:</label>
						<div class="col-sm-8">
							<input class="form-control" id="settingsServiceNameBusiness"
								name="settingsServiceNameBusiness" placeholder="Name Business"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Select Service:</label>
						<div class="col-sm-8">
							<select class="form-control" id="serviceSelect"
								name="serviceSelect">
								<option value="0">Select the option</option>
								<%
									ArrayList<Category> categorys = new ArrayList<Category>();
									categorys.addAll(category.loadCategoryInformation(request));
									if (categorys != null) {
										for (int i = 0; i < categorys.size(); i++) {
											Category cat = (Category) categorys.get(i);
											out.append("<option value='" + cat.getCategory_id() + "'>" + cat.getCategory_name() + "</option>");
										}
									}
								%>

							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Tiny discription:</label>
						<div class="col-sm-8">
							<input class="form-control" id="tinyDescription"
								name="tinyDescription" placeholder="Tiny Description" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Full discription:</label>
						<div class="col-sm-8">
							<textArea class="form-control" name="fullDescription"
								id="fullDescription" placeholder="Full Description" required></textArea>
						</div>
					</div>
					<div style="margin-left: 73%;">
						<input type="submit" class="btn btn-success" value="Save" /> <input
							type="hidden" id="settings_user_id" name="settings_user_id"
							value='<%=request.getAttribute("user_id")%>' /> <input
							type="hidden" name="logica" id="logica" value="SService">
						<input type="hidden" name="action" id="action" value="addService">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>



<div id="openEditServiceModal" class="modal fade" role="dialog">
	<!-- Modal de Editar -->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" id="modal-header">
				<button type="button" id="closeLogin" class="close"
					data-dismiss="modal">&times;</button>
				<h4 class="modal-title" id="headerLogin">Edit</h4>
			</div>
			<%
				Service userService = (Service) request.getAttribute("objectUserService");
				if (userService != null) {
					out.append("<form method='POST' action='index' class='form-horizontal'>" + "<div class='modal-body'>"
							+ "<div class='form-group'>" + "<label class='control-label col-sm-2'>Name Business:</label>"
							+ "<div class='col-sm-8'>"
							+ "<input class='form-control' id='serviceUserNameBusiness' name='serviceUserNameBusiness' value='"
							+ userService.getService_name() + "'>" + "</div>" + "</div>" + "<div class='form-group'>"
							+ "<label class='control-label col-sm-2'>Select Service:</label>" + "<div class='col-sm-8'>"
							+ "<select class='form-control' id='serviceSelectEdit' name='serviceSelectEdit' required>"
							+ "<option value=''>Select one category</option>");
			%>
			<%
				ArrayList<Category> serviceCategory = new ArrayList<Category>();
					serviceCategory.addAll(category.loadCategoryInformation(request));
					if (serviceCategory != null) {
						for (int i = 0; i < serviceCategory.size(); i++) {
							Category cat = (Category) serviceCategory.get(i);
							if (userService.getService_category().getCategory_id() == cat.getCategory_id()) {
								out.append("<option value='" + cat.getCategory_id() + "' selected>" + cat.getCategory_name()
										+ "</option>");
							} else {
								out.append("<option value='" + cat.getCategory_id() + "'>" + cat.getCategory_name()
										+ "</option>");
							}
						}
					}
			%>
			<%
				out.append("</select>" + "</div>" + "</div>" + "<div class='form-group'>"
							+ "<label class='control-label col-sm-2'>Tiny discription:</label>" + "<div class='col-sm-8'>"
							+ "<input class='form-control' id='tinyDescriptionEdit' name='tinyDescriptionEdit' value='"
							+ userService.getService_tiny_description() + "'>" + "</div>" + "</div>"
							+ "<div class='form-group'>" + "<label class='control-label col-sm-2'>Full discription:</label>"
							+ "<div class='col-sm-8'>"
							+ "<textArea class='form-control' name='fullDescriptionEdit' id='fullDescriptionEdit'>"
							+ userService.getService_full_description() 
							+"</textArea>" + "</div>" + "</div>"
							+ "<div style='margin-left: 73%;'>"
							+ "<input type='submit' class='btn btn-success' value='SaveService' />"
							+ "<input type='hidden' id='settings_user_id' name='settings_user_id'/>"
							+ "<input type='hidden' name='logica' id='logica' value='SUser'>"
							+ "<input type='hidden' name='service_user_id' value='"+userService.getService_id()+"'>"
							+ "<input type='hidden' name='action' id='action' value='editServiceUser'>" + "</div>" + "</div>"
							+ "</form>");
				}
			%>
		</div>
	</div>
</div>


<script>
	$(document).ready(function() {
		var x = $("#errorLogin").val();
		var y = $("#editServiceModal").val();
		
		if (x == 10) {
			$("#btnLogin").click();
			$("#alertLogin").css("display", "block");
		} else {

		}

		if (y == 1) {
			$("#btnOpenEditService").click();
		} else {

		}
	});

	$(".editService").on("click", function() {
		$("#tableUserService").css("display", "none");
		$("#name_service_id").val($(this).attr("id"));
		$("#btnEditService").click();
	});

	$(".deleteService").on("click", function() {
		$("#user_service_id_delete").val($(this).attr("id"));
		$("#btnDeleteService").click();
	});

	$("#addService").on("click", function() {
		$("#tableUserService").css("display", "none");
		$("#btnOpenAddService").click();

	});
</script>
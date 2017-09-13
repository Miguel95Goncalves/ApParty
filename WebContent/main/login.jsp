<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">

<button id="btnRegister" style="display: none;" type="button" class="btn btn-default" data-toggle="modal"
						data-target="#modalRegister"></button>



<!-- Modal de Login-->
<div id="modalLogin" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button id="closeModal" type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Login</h4>
			</div>
			<form method="post" action="index">
				<div class="modal-body">
					<input type="hidden" name="logica" value="SUser"> 
					<input type="hidden" name="action" value="login">
					<div class="form-group">
						<input class="form-control" type="email" name="ip_email_login" id="ip_email_login"
							placeholder="E-mail" autocomplete="off">
					</div>
					<div class="form-group">
						<input class="form-control" type="password" name="ip_password_login" id="ip_password_login"
							placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<div class=text-right>
							<input class="btn btn-success" type="submit" name="login" id="login"
								value="Login">
						</div>
					</div>
				</div>
			</form>
			<div class="modal-footer">
				<a id="openRegister" href="#">Create Account!</a>
			</div>
		</div>

	</div>
</div>

<!-- Modal de Registo-->
<div id="modalRegister" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Registo</h4>
			</div>
				<div class="modal-body">
					
				</div>
			<div class="modal-footer">
			</div>
		</div>

	</div>
</div>

<script>
$( "#openRegister" ).on( "click", function() {
	$( "#closeModal" ).trigger( "click" );
	$( "#btnRegister" ).trigger( "click" );
	});
</script>
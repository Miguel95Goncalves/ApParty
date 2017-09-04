<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">

<button id="btnRegister" style="display: none;" type="button" class="btn btn-default" data-toggle="modal"
						data-target="#modalRegister"></button>

<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">ApParty</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#modalLogin">Login</button>
				</li>
			</ul>
		</div>
	</div>
</nav>

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
					<input type="hidden" name="logica" value="SUser"> <input
						type="hidden" name="acao" value="login">
					<div class="form-group">
						<input class="form-control" type="email" name="user_email"
							placeholder="E-mail" autocomplete="off">
					</div>
					<div class="form-group">
						<input class="form-control" type="password" name="user_password"
							placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<div class=text-right>
							<input class="btn btn-success" type="submit" name="login"
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
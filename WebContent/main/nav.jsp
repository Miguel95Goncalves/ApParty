<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<button id="btnChoise" name="btnChoise"style="display: none;" type="button"  data-toggle="modal" data-target="#modalChoise"></button>
<button id="btnRegistNormal" name="btnRegistNormal" style="display: none;" type="button"  data-toggle="modal"data-target="#modalRegisterNormal"></button>
<button id="btnRegistProfessional" name="btnRegistProfessional" style="display: none;" type="button" data-toggle="modal" data-target="#modalRegisterProfessional"></button>

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
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right" style="display:flex;">
				<%
					HttpSession sessao = request.getSession(true);
					if (sessao.getAttribute("user_id") == null) {
				%>
				<li class="dropdown"><a>
					<button class="btn btn-default" id="btnLogin" name="btnLogin" data-toggle="modal" data-target="#Login">Login</button> 
					<input type="hidden" id="errorLogin" name="errorLogin" value='<%=request.getAttribute("erroLogin")%>' />
					
				</a></li>
				<%
					} else {
				%>
				<li><a href="index?pag=profile">Perfil</a></li>
				<li><a href="index?pag=friends">Friends</a></li>
				<li><a href="index?pag=friends">qwe: <%=sessao.getAttribute("user_user_type_id")%></a></li>
				<li><a>
						<form method="POST" action="index">
							<input type="hidden" name="action" id="action" value="logout">
							<input type="hidden" name="logica" id="logica" value="SUser">
							<input type="submit" class="btn btn-default" name="sair" value="Logout">
						</form>
				</a></li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</nav>


<div id="Login" class="modal fade" role="dialog">	<!--  PARA FAZER O LOGIN -->
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header" id="modal-header">
				<button type="button" id="closeLogin" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="display: block" id="headerLogin">Login</h4>
			</div>
			<form method="POST" action="index">
				<div class="modal-body">
					<div id="alertLogin" class="alert alert-danger" role="alert" style="display: none;">
						<strong>Ups!</strong> Email or Password are not valid
					</div>
					<div class="input-group"  id="email_div"> <!-- AQUI E A DIV DO EMAIL -->
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input class="form-control" type="email" id="ip_email_login" name="ip_email_login" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Email" required>
					</div>
						
						<div class="input-group"  id="pass_div" style="margin-top: 20px;">  <!-- AQUI E A DIV DA PASSWORD -->
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input class="form-control" type="password" id="ip_password_login" name="ip_password_login" placeholder="Password" required>
						</div>
					
					<div class="modal-footer" style="margin-bottom: 20px; margin-top:20px;">
						<div class="text-right">
							<a id="openChoise" href="#" name="openChoise"> Create Acount!</a> 
							<input type="submit" class="btn btn-success" value="Login"> 
							<input type="hidden" name="action" id="action" value="login">
							<input type="hidden" name="logica" id="logica" value="SUser">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!--  MAIN DIV  DO LOGIN -->

<!-- --------------------------------------------------------------------------------------------------------------------------- -->


<div id="modalChoise" class="modal fade" role="dialog">
	<!--  ESCOLHER CONTA-->
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header" id="modal-header" style="display: block;">
				<button type="button" id="closeChoise" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Type Acount</h4>
			</div>
			<div id="typeAcount" class="text-center">
				<a class="btn btn-default" id="openRegistNormal" style="margin-rigth: 10px;">Normal</a> 
				<a class="btn btn-danger" id="openRegistProfessional">Professional</a>
			</div>
		</div>
	</div>
</div><!--  MAIN DIV  DO ESCOLHER CONTA -->
<!-- --------------------------------------------------------------------------------------------------------------------------- -->

<div id="modalRegisterNormal" class="modal fade" role="dialog">	<!--  CRIAR CONTA NORMAL-->
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header" id="modal-header" style="display: block;">
				<button type="button" id="closeRegistN" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Normal Acount</h4>
			</div>
				<form method="POST" action="index">
					<div class="modal-body">
						<div class="form-group">
						  	<label>Nick</label>
						    <input type="text" class="form-control" id="ip_nick_normal_na" name="ip_nick_na" placeholder="Nick" required/>
						</div>
			
						<div class="form-group">
						  	<label>Name</label>
						    <input type="text" class="form-control" id="ip_name_normal_na" name="ip_name_na" pattern="[A-Z a-z]{3,50}" placeholder="Name" required/>
						</div>
						
						<div class="form-group">
							<label>Email</label>
							<input class="form-control" type="email" id="ip_email_na" name="ip_email_na" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Email" required>
						</div>
						
						<div class="form-group">
							<label>Contact</label>
						    <input type="text" class="form-control"  id="ip_contact_na" name="ip_contact_na"  pattern="[0-9] [9-13]{9,13}" placeholder="Contact" title="Apenas permitido numeros, maximo de 13 digitos" required/>	
							
						</div>
						
						<div class="form-group">
						  	<label>Data nascimento</label>
						    <input type="date" class="form-control" id="ip_birth_na" name="ip_birth_na" placeholder="1995-04-25" required/>
						</div>
	    
					    <div class="form-group">
						    <label>Password</label>
						    <input type="password" class="form-control" id="ip_password_na" name="ip_password_na" placeholder="Password" required/>
						</div>
							
						<div class="form-group">
						    <label>Re-Password</label>
						    <input type="password" class="form-control" id="ip_repassword_na" name="ip_repassword_na" placeholder="Re-Password" oninput="vericarPassword()" required/>
						    <label class="label label-danger" id="lb_repassword_na" style="display: none">Password don´t macth</label>
						    <label class="label label-success" id="lb_repassword_na_succes" name="lb_repassword_pc" style="display: none">Password macth</label>
						</div>
						
						<div class="modal-footer" style="margin-bottom: 20px; margin-top:20px;">
							<div class="text-right">
								<input type="submit" class="btn btn-success" value="Regist"> 
								<input type="hidden" name="action" id="action" value="registNormal">
								<input type="hidden" name="logica" id="logica" value="SUser">
							</div>
						</div>
				</div>
			</form>
		</div>
	</div>
</div><!--  MAIN DIV  DO CREATE ACOUNT NORMAL -->

<!-- --------------------------------------------------------------------------------------------------------------------------- -->

<div id="modalRegisterProfessional" class="modal fade" role="dialog"> <!--  CRIAR CONTA PROFESSIONAL-->
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header" id="modal-header">
				<button type="button" id="closeRegistP" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Professional Acount</h4>
			</div>
			<form role="form" method="POST" action="index">
				<div class="modal-body">
					<div class="form-group">
						<label>Name</label> <input type="text" class="form-control" id="ip_name_pc" name="ip_name_pc" placeholder="Name" required />
					</div>

					<div class="form-group">
						<label>Contact</label> <input type="text" class="form-control" id="ip_contact_pc" name="ip_contact_pc" pattern="[0-9] [9-13]{9,13}" placeholder="Contact" title="Apenas permitido numeros, maximo de 13 digitos" required />
						<!--  <label class="label label-danger" id="lb_contact">Apenas numeros</label> -->
					</div>

					<div class="form-group">
						<label>Data nascimento</label> <input type="date" class="form-control" id="ip_birth_pc" name="ip_birth_pc" placeholder="1995-04-25" required />
					</div>

					<div class="form-group">
						<label>Email</label>
						<input type="email" class="form-control" id="ip_email_pc" name="ip_email_pc" placeholder="exemple@example.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required />
					</div>

					<div class="form-group">
						<label>Password</label>
						 <input type="password" class="form-control" id="ip_password_pc" name="ip_password_pc" placeholder="Password" required />
					</div>

					<div class="form-group">
						<label>Re-Password</label>
						<input type="password" class="form-control" id="ip_repassword_pc" name="ip_repassword_pc" placeholder="Re-Password" oninput="vericarPasswordPc()" required />
						 <label class="label label-danger" id="lb_repassword_pc" name="lb_repassword_pc" style="display: none">Password don´t macth</label>
						 <label class="label label-success" id="lb_repassword_pc_success" name="lb_repassword_pc" style="display: none">Password macth</label>
					</div>
					<div class="modal-footer" style="margin-bottom: 20px; margin-top:20px;">
							<div class="text-right">
								<input type="submit" class="btn btn-success" value="Regist"> 
								<input type="hidden" name="action" id="action" value="registProfessional">
								<input type="hidden" name="logica" id="logica" value="SUser">
							</div>
						</div>
				</div>
			</form>
		</div>
	</div>
</div> <!--  MAIN DIV CONTA PROFESSIONAL -->
<script>

$(document).ready(function() {
    var x = document.getElementById("errorLogin");
    
    	if(x.value == 10){
    		$("#btnLogin").trigger("click");
    		$("#alertLogin").css("display", "block");
    	}else{
    		
    	}
});

	$("#openChoise").on("click", function() {
		$("#closeLogin").trigger("click");
		$("#btnChoise").trigger("click");
	});

	$("#openRegistNormal").on("click", function() {
		$("#closeChoise").trigger("click");
		$("#btnRegistNormal").trigger("click");
	});

	$("#openRegistProfessional").on("click", function() {
		$("#closeChoise").trigger("click");
		$("#btnRegistProfessional").trigger("click");
	});

	$("#closeLogin").on("click", function() {
		$('#alertLogin').css("display", "none");
	});
	
function vericarPassword() {// VERIFICAR SE O PRIMEIRO CAMPO É IGUAL AO SEGUNDO (CONTA NORMAL)
		var x = document.getElementById("ip_password_na");
		var y = document.getElementById("ip_repassword_na");

		if (x.value != y.value) {
			$('#lb_repassword_na').css("display", "block");
			$('#lb_repassword_na_succes').css("display", "none");
			return false;
		} else {
			$('#lb_repassword_na').css("display", "none");
			$('#lb_repassword_na_succes').css("display", "block");
			return true;
		}
}

function vericarPasswordPc() {// VERIFICAR SE O PRIMEIRO CAMPO É IGUAL AO SEGUNDO (CONTA PROFESSIONAL)
		var x = document.getElementById("ip_password_pc");
		var y = document.getElementById("ip_repassword_pc");

		if (x.value != y.value) {
			$('#lb_repassword_pc').css("display", "block");
			$('#lb_repassword_pc_success').css("display", "none");

			return false;
		} else {
			$('#lb_repassword_pc').css("display", "none");
			$('#lb_repassword_pc_success').css("display", "block");
			return true;
		}
}
</script>

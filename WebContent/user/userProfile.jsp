  <%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="services.SUser"%>
	<% SUser sUser = new SUser();%>
	<% sUser.loadClientInformation(request);%>
	
  <div style="height: 270px;"> 
	  <div style=" height: 200px; background-color: red; padding-top: 125px">
	      <div class="container">
	 		 <img src="http://meet4dance.com/upload/photos/d-avatar.jpg" alt="Avatar" class="avatar">
			  <div class="overlay">
			    <img class="image" src="http://i.imgur.com/8LaeZcN.png">
			  </div>
			</div>
	    </div>
    </div>
    
    <div style="margin-bottom: 3%">
      <h2 style="text-align: center"><%=request.getAttribute("user_name")%></h2>
    </div>
    
 <%
 HttpSession sessao = request.getSession(true);
 if(sessao.getAttribute("user_user_type_id").equals(1)){		
	 %><jsp:include page="/user/userNormalForm.jsp"/><%
   			
 }else{
	 %><jsp:include page="/user/userProfessionalForm.jsp"/><%
} %>
  
  <div style="display: none;">
  	<form method="POST" action="index">
		<input type="hidden" name="action" id="action" value="logout">
		<input type="hidden" name="logica" id="logica" value="SUser">
		<input type="submit" class="btn btn-default" name="btnSair" id="btnSair" value="Logout">
		<input tupe="hidden" name="errorPassword" id="errorPassword" value='<%request.getAttribute("password_error_client");%>'>
	</form>
	
	
</div>
<style>

#main {
	display: flex;
}

#formUserSettings{
	width:50%;
	height:50%;
	margin: 5%;
}

#settingsService{
	width:40%;
	height:50%;
	margin-left:30%;
	margin-top: 5%;
}
	
#choiseSettings {
	width: 10%;
	margin: 5%;
	
}
.nameDiv,.nickDiv,.emailDiv,.passwordDiv,.birthdayDiv,.contactDiv,.newPasswordDiv{
	display: flex;
	
}

#editName,#editNick,#editContact,#editEmail,#editPass,#editBirthday, #editNewPass, #editConfirmPass, #editConfirmEdit{
	margin: 5%; 
	margin-top:5px;
}

 #editConfirmPassSuccess, #editConfirmPassError{
 	margin: 7.5%;
 	margin-top: 5px;
 }


 .container {
  position: relative;
  width: 150px;
  height: 150px;
  border-radius: 100px;
}

.avatar {
  display: block;
  width: 150px;
  height: 150px;
  border-radius: 100px;
}

.image{
  width: 150px;
    height: 150px;
    border-radius: 100px;
}

.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  opacity: 0;
  transition: .5s ease;
  background-color: #008CBA;
  border-radius: 100px;
}

.container:hover .avatar{
  opacity: 0.8;
}

.container:hover .overlay {
  opacity: 0.5;
}

.image {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
}
</style>
<script>


$(document).ready(function() {
    var x = $("errorLogin").val();
    var y = $("password_error_client").val();
    
    	if(x == 10){
    		$("#btnLogin").click();
    		$("#alertLogin").css("display", "block");
    	}else{
    		
    	}
    	
    	if(y == 1){
    		$("#alertPassword").css("display", "block");
    	}else{
    		
    	}
});

$("#settingsServices").click(function(){
	$("#settingsServices").css("display", "none");
	$("#settingsServices2").css("display", "block");
	$("#tableUserService").css("display", "block");
});

$("#settingsServices2").click(function(){
	$("#settingsServices").css("display", "block");
	$("#settingsServices2").css("display", "none");
	$("#tableUserService").css("display", "none");
	
});

$("#formUserSettings").submit(function( event ) {
	$('#settings_password_actual').prop("disabled", false);
	$('#settings_name_new').prop("disabled", false);
	$('#settings_nick_new').prop("disabled", false);
	$('#settings_email_new').prop("disabled", false);
	$('#settings_contact_new').prop("disabled", false);
	$('#settings_birthday_new').prop("disabled", false);
	});


  $("#listLogout").on("click", function() {
		$("#btnSair").trigger("click");
	});
   
	$("#settingsProfile").on("click", function(){
		$('#formUserSettings').css("display", "block");
		$('#settingsProfile').css("display","none");
		$('#settingsProfile2').css("display","block");
		$("#settingsService").css("display", "none");
	});

	$("#settingsProfile2").on("click", function() {
		$('#formUserSettings').css("display", "none");
		$('#settingsProfile2').css("display","none");
		$('#settingsProfile').css("display","block");
		$("#settingsService").css("display", "none");
	});

	$('#editName').on("click", function(){
		$('#settings_name_new').prop("disabled", false);
	});

	$('#editNick').on("click", function(){
		$('#settings_nick_new').prop("disabled", false);
	});

	$('#editEmail').on("click", function(){
		$('#settings_email_new').prop("disabled", false);
	});

	$('#editContact').on("click", function(){
		$('#settings_contact_new').prop("disabled", false);
	});

	$('#editBirthday').on("click", function(){
		$('#settings_birthday_new').prop("disabled", false);
	});

	$('#editPass').on("click", function(){
		$('#settings_password_actual').css("display", "none");
		$('#settings_old_password').css("display", "block");
		$('#settings_old_password').prop("disabled", false);
	});

	function changePass(){
		var x = document.getElementById("settings_old_password").value;
		var y = document.getElementById("settings_password_actual").value;
			
		if(x != y){
			$('#divConfirmPassword').css("display", "none");
			$('#divPasswordNew').css("display", "none");
		}else{
			$('#divConfirmPassword').css("display", "block");
			$('#divPasswordNew').css("display", "block");
			$('#settings_old_password').prop("disabled", true);
		}
			
	}

	function changeConfirmPassword(){
		var f = document.getElementById("settings_new_password_confirm");
		var a = document.getElementById("settings_new_password");
		
		if(f.value !=a.value ){
			$('#editConfirmEdit').css("display", "none");
			$('#editConfirmPassSuccess').css("display","none");
			$('#editConfirmPassError').css("display", "block");
		}else{
			$('#editConfirmEdit').css("display", "none");
			$('#editConfirmPassSuccess').css("display", "block");
			$('#editConfirmPassError').css("display","none");
		}
	}

</script>


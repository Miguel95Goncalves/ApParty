
function verificarPassword(){	
	var pass =  document.getElementById("ip_password");
	var pass =  document.getElementById("ip_password");

	if(nome.value != confpass.value ){
		$('#lb_repassword').css("display","block");
		return false;
	}else{
		$('#lb_repassword').css("display","none");
		return true;
	}	
}

function verificarContato(){
	
	var contato =  document.getElementById("ip_contact");
	
	if(isNaN(contato)){
		$('#lb_contact').css("display", "block");
		return false;
	}else{
		$('#lb_contact').css("display", "none");
		return false;
	}
}

function activeChoise(){
	$('#typeAcount').css("display", "block"); 
	$('#formLogin').css("display", "none");
	$('#headertypeAcount').css("display", "block");
	$('#headerLogin').css("display", "none");
	
}

function activeNormal(){
	$('#typeAcount').css("display", "none");
	$('#headertypeAcount').css("display", "none");
	$('#modal-header').css("display", "none");
	$('#login').css("display", "none");
	$('#ola').css("display", "none");
	$('#regist').css("display", "block");
	$('#headerCreateAcount').css("display", "block");
	$('#form_nick').css("display", "block");
	$('#form_birth').css("display", "block");
}

function activeProfessional(){
	$('#typeAcount').css("display", "none");
	$('#headertypeAcount').css("display", "none");
	$('#modal-header').css("display", "none");
	$('#login').css("display", "none");
	$('#regist').css("display", "block");
	$('#form_service').css("display", "block");
}



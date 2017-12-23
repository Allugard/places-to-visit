var flag = 0;
$('document').ready(function(){
    $('.menu').click(function () {
    	$('.dropdown-menu').animate({width:'toggle'},350);
   		$('.dropdown-menu').css('display','flex');
   		if(flag == 0){
    		$('#social').css('display','none');
    		flag = 1;
   		}else{
    		$('#social').css('display','flex');
    		flag = 0;
    	}
    });
    
});	


function validate(form){
	var password, cPassword;
	password = form.password.value;
	cPassword = form.cpassword.value;
	if(password != cPassword){
		document.getElementById('caution').innerHTML = "Passwors don't match";
		return false;
	}else{
		return true;
	}
}
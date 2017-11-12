
$('document').ready(function(){
    $('.menu').click(function () {
    	$('.dropdown-menu').animate({width:'toggle'},350);
   		$('.dropdown-menu').css('display','flex');
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
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
document.getElementById('submit').addEventListener('click', sendData);
function sendData(){
	var dataForm = JSON.stringify(formToJson($("form#form").serializeArray()));
    fetch('http://192.168.99.100:9001/sign-up', {
        method: 'put',
        type: 'cors',
        headers: {"Content-Type": "application/json"},
        body: dataForm
    }).then(function(response) {
        if (response.status !== 200) {
            event.preventDefault();
            return;
        }
      window.location.href="http://localhost:9000/signIn"; 
    });
}


function formToJson (formArray) {
	var returnArray = {};
  for (var i = 0; i < formArray.length; i++){
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}

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
function sendData(){
//document.getElementById("submit").addEventListener('click',function (){
	var dataForm = JSON.stringify(formToJson($("form#form").serializeArray()));
	console.log(dataForm);

	$.ajax({
	  method: "PUT",
	  url: 'http://192.168.99.100:9001/sign-up',
	  data: dataForm,
	  dataType: 'json',
	  headers: {"Content-Type": "application/json"}
	  })
	  .done((result) => {
	    console.log(result);
	  })
	  .fail((jqXHR, textStatus) => {
	    console.log(jqXHRObject.responseText);
	    console.log(jqXHRObject.responseText);
	    console.log(jqXHRObject.responseText);

	});
	
	console.log(dataForm);
	return false;
//});
}

function formToJson (formArray) {
	var returnArray = {};
  for (var i = 0; i < formArray.length; i++){
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}

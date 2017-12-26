function sendServer(){
//document.getElementById("submit").addEventListener('click',function (){
	var dataForm = JSON.stringify(formToJson($("form#form").serializeArray()));
	console.log(dataForm);

	$.ajax({
	  method: "POST",
	  url: 'http://192.168.99.100:9001/login',
	  data: dataForm,
	  dataType: 'json',
	  headers: {"Content-Type": "application/json"}
	  })
	  .done((result) => {
	    result.getResponseHeader('authorization');
	  })
	  .fail((jqXHR, textStatus) => {
	    console.log(jqXHRObject.responseText);
	    console.log(jqXHRObject.responseText);
	    console.log(jqXHRObject.responseText);

	});
	
	console.log(dataForm);
//});
}

function formToJson (formArray) {
	var returnArray = {};
  for (var i = 0; i < formArray.length; i++){
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}

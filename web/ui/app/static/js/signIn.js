
document.getElementById("submit").addEventListener('click',function (){
	var dataForm = JSON.stringify(formToJson($("form#form").serializeArray()));

    fetch('http://192.168.99.100:9001/login', {
        method: 'post',
		type: 'cors',
        body: dataForm
    }).then((response) => {
        if (response.status !== 200) {
            event.preventDefault();
            alert("response.status "+response.status);
            return;
        }
        return response.json();
    }).then((data) => {
        document.cookie = "name = Bearer " + data.token;
        window.location.href="http://localhost:9000/account";
    });
})

function formToJson (formArray) {
	var returnArray = {};
  for (var i = 0; i < formArray.length; i++){
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}

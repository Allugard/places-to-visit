function getProducts(){ 
$.ajax({
        type : "GET",
        contentType : "application/json",
        url : "http://www.xyzservice.com/api/product",
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            // now you have "data" which is in json format-same data that is displayed on browser.
            displayDate(date);      
        },
        error : function(e) {
            //do something
        },
        done : function(e) {
            //do something
        }
    });
}

function displayDate(){
    //your codes to parse and display json data  in html table in your page.
}
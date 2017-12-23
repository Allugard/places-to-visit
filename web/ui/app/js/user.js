var request = require('request');

var createUser = function (body,res) {
    request({
        url: "http://127.0.0.1:9001/users",
        json: body,
        method: 'PUT'
    }, function (error, response, body) {
        if (error.code == 200){
            res.sendFile('signIn.html', { root: './app/static/pages' });
        } else {
            res.redirect('/signUp');
        }
    })
}

module.exports = function(app, router) {
    router.post('/sign-up', function(req, res) {
        createUser(req.body,res);
        console.log("console log");
    });
    app.use('/user', router);
};




/*function getProducts(){
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
}*/
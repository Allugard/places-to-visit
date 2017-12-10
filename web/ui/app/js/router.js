var request = require('request');

module.exports = function(app, router){

    //Here comes frontend pages
    router.get('/', function(req, res) {
        res.sendFile('index.html', { root: './app/static/pages' });
    });

    router.all('/about', function(req, res) {
        res.sendFile('about.html', { root: './app/static/pages' });
    });

    router.all('/signIn', function(req, res) {
        res.sendFile('signIn.html', { root: './app/static/pages' });
    });

    router.all('/signUp', function(req, res) {
        res.sendFile('signUp.html', { root: './app/static/pages' });
    });

    router.all('/account', function(req, res) {
        res.sendFile('account.html', { root: './app/static/pages' });
    });

    router.all('/map', function(req, res) {
        res.sendFile('map.html', { root: './app/static/pages' });
    });

    router.post('/users', function(req, res) {
        console.log(req.body);
        createUser(req.body);
        res.sendFile('signIn.html', { root: './app/static/pages' });
    });

    app.use('/', router);
};

function createUser(body){
    request({
        url: "http://127.0.0.1:9001/users",
        json: body,
        method: 'PUT'
    }, function(error, response, body) {
        res.sendFile('signIn.html', { root: './app/static/pages' });
    })
}
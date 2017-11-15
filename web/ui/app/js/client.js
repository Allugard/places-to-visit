module.exports = function(app, router){

    //Here comes frontend pages
    router.get('/', function(req, res) {
        res.sendFile('index.html', { root: 'static/pages' });
    });

    app.use('/', router);
}
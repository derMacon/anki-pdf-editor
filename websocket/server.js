var express = require('express');
var ws = require('./ws');
var pageSetter = require('./ws');

var app = express()
var pageNum = 42;

app.get('/', function (req, res) {
    res.sendFile(__dirname + '/pdf_viewer/index.html');
    // res.sendFile(__dirname + '/ws.html');
})

app.get('/setPageNum/:pageNum', function (req, res) {
    pageSetter(req.params.pageNum);
    res.json(req.params);
})

app.get('/getPageNum', function (req, res) {
    res.json(pageNum);
})



app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})

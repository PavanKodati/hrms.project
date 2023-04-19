var http = require('http');
var fs = require('fs');
var index = fs.readFileSync('index.html');
var home = fs.readFileSync('home.html');
http.createServer(function (req, res) {
  res.writeHead(200, { 'Content-Type': 'text/html' });
  if (req.url == '/index.html' || req.url == '/') {
    res.end(index);
  } else {
    res.end(home);
  }
}).listen(3000);
//var container = document.getElementById('info');

var requ = new XMLHttpRequest();
// requ.open('GET', 'http://localhost:8080/register');
requ.open('POST', 'http://127.0.0.1:8765', true);
requ.onreadystatechange = function() {
    var output = JSON.parse(requ.responseText);
    console.log(output);
    // console.log(requ.responseText);
};

var input = {
    "name": 42
};

requ.send(JSON.stringify(input3));
console.log();

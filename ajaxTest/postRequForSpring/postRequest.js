var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;


var requ = new XMLHttpRequest();
// requ.open('GET', 'https://jsonplaceholder.typicode.com/todos/1');
requ.open('POST', 'http://localhost:8080/register', true);
requ.onreadystatechange = function() {
    var output = JSON.parse(requ.responseText);
    console.log(output);
    // console.log(requ.responseText);
};
var input2 = {
	"name": 42
};


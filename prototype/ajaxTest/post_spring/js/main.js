var requ = new XMLHttpRequest();

requ.onload = function () {
	console.log(this.responseText);
}

requ.open('POST', 'http://localhost:8080/register');
requ.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
//requ.send("name=silas");
let input = {
    "name": "silas"
};
input = JSON.stringify(input);
console.log('before send: ' + input);
requ.send(input);


/*
requ.open('POST', 'http://localhost:8080/register');
// requ.open('POST', 'http://127.0.0.1:8765', true);
requ.onreadystatechange = function() {
	console.log('js ajax');
	console.log('response: ' + requ.responseText);
    // var output = JSON.parse(requ.responseText);
    // console.log(output);
    // console.log(requ.responseText);
};

var input = {
    "name": "silas"
};

requ.send(JSON.stringify(input));
console.log();
*/

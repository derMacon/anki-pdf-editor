fetch('http://localhost:8080/register', {
	method: 'POST', 
	body: JSON.stringify({id: '200'})
}).then(response => {
	if (respnse.ok) {
		return repsonse.json();
	}
	throw new Error('Request failed!');
}, networkError => console.log(networkError.message)
).then(jsonResponse => {
	console.log(jsonResponse);
});

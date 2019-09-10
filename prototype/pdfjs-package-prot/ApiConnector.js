const urlApiServer = 'http://localhost:8080/';
const addCard_endpoint = urlApiServer + 'addCard';
const retrievePdf_endpoint = urlApiServer + 'retrievePdf';


function generatePostSyntax(cardObj) {
  let str = JSON.stringify(cardObj);
  return str.replace(/[{}"]/g, '').replace(/,/g, '&').replace(/:/g, '=');
}

function requestPost(postSyntax, url) {
  const xhr = new XMLHttpRequest();
  xhr.onload = function () {
  	console.log(this.responseText);
  }
  xhr.open('POST', url);
  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  console.log('before send: ' + postSyntax);
  xhr.send(postSyntax);
}

export const ApiConnector = {
  submitCard(cardObj) {
    console.log(`debug:\n
    front ${cardObj.front}\n
    back ${cardObj.back}\n
    tags ${cardObj.tags}`);

    const postSyntax = generatePostSyntax(cardObj);
    console.log('before send: ' + postSyntax);

    requestPost(postSyntax, addCard_endpoint);
  },

  retrievePdf(name) {
    console.log(`debug name: ${name}`);
    requestPost(`fileName=${name}`, retrievePdf_endpoint);
  }
}

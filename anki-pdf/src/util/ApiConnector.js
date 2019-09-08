const urlApiServer = 'http://localhost:8080/addCard';

export const ApiConnector = {
  submitCard(cardObj) {
    console.log(`debug:\n
      front ${cardObj.front}\n
      back ${cardObj.back}\n
      tags ${cardObj.tags}`);

      var xhr = new XMLHttpRequest();
      xhr.onload = function () {
      	console.log(this.responseText);
      }

      xhr.open('POST', urlApiServer);
      xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

      const postSyntax = generatePostSyntax(cardObj);
      console.log('before send: ' + postSyntax);
      xhr.send(postSyntax);
  }
}

function generatePostSyntax(cardObj) {
  let str = JSON.stringify(cardObj);
  return str.replace(/[{}"]/g, '').replace(/,/g, '&').replace(/:/g, '=');
}

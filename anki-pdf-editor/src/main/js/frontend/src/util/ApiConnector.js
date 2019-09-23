const urlApiServer = 'http://localhost:8080/';

const addCard_endpoint = urlApiServer + 'addCard';
const serveSelectedPdf_endpoint = urlApiServer + 'serveSelectedPdf';
const getPdfName_endpoint = urlApiServer + 'selectedPdfName';
const selectNewPdf_endpoint = urlApiServer + 'selectNewPdf';
const selectDeck_endpoint = urlApiServer + 'selectDeck';

// todo check if needed
// const retrievePdf_endpoint = urlApiServer + 'retrievePdf';


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

function requestGet(url) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}


export const ApiConnector = {
  submitCard(cardObj) {
    const postSyntax = generatePostSyntax(cardObj);
    console.log('before sending json: ' + postSyntax);
    requestPost(postSyntax, addCard_endpoint);
  },

  selectedPdf: serveSelectedPdf_endpoint,
  selectedPdfName() {
    return requestGet(getPdfName_endpoint);
  },
  selectNewPdf() {
    requestGet(selectNewPdf_endpoint);
    window.location.reload();
  },

  selectDeck() {
    requestGet(selectDeck_endpoint);
    window.location.reload();
  }

}

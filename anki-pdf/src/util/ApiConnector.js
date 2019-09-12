const urlApiServer = 'http://localhost:8080/';

const addCard_endpoint = urlApiServer + 'addCard';
const retrievePdf_endpoint = urlApiServer + 'retrievePdf';
const turnCurrPage_endpoint = urlApiServer + 'turnCurrPage';
const turnPrevPage_endpoint = urlApiServer + 'turnPrevPage';
const turnNextPage_endpoint = urlApiServer + 'turnNextPage';

const tempDir_urlPrefix = 'tempPages/pdf/';
const urlCurrPage = urlApiServer + tempDir_urlPrefix + 'currentPage.pdf';
const urlNextPage = urlApiServer + tempDir_urlPrefix + 'nextPage.pdf';
const urlPrevPage = urlApiServer + tempDir_urlPrefix + 'previousPage.pdf';


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
    // var xmlHttp = new XMLHttpRequest();
    // xmlHttp.onreadystatechange = function() {
    //     if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
    //         return xmlHttp.responseText;
    //     }
    // }
    // xmlHttp.open("GET", url, true); // true for asynchronous
    // xmlHttp.send(null);

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
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


  turnPrevPage() {
    requestPost('', turnNextPage_endpoint);
  },

  turnNextPage() {
    requestGet(turnNextPage_endpoint);
  },

  currPage_url: urlCurrPage,
  prevPage_url: urlPrevPage,
  nextPage_url: urlNextPage,

  getNextPage_url() {
    return requestGet(urlApiServer + 'getNextPage');
  },

  getPrevPage_url() {
    console.log('uri: ' + requestGet(urlApiServer + 'getPrevPage'));
    return requestGet(urlApiServer + 'getPrevPage');
  },




  retrievePdf(name) {
    console.log(`debug name: ${name}`);
    requestPost(`fileName=${name}`, retrievePdf_endpoint);
  }
}

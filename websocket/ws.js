var WebSocketServer = require('ws').Server,
  wss = new WebSocketServer({port: 40510})

let pageNum;
let webSocket;

wss.on('connection', function (ws) {
  ws.on('message', function (message) {
    console.log('received: %s', message + pageNum)
    ws.send(message + pageNum);
  })
  webSocket = ws;

  oldNum = pageNum;
  setInterval(
    () => {
      if (oldNum != pageNum) {
        ws.send(pageNum)
      };
      oldNum = pageNum;
    },
    1000
  )
})

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

const pageSetter = function(num) {
  pageNum = num;
}
module.exports = pageSetter;

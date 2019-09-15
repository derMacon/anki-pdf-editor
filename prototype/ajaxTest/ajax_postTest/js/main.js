//var container = document.getElementById('info');

var requ = new XMLHttpRequest();
// requ.open('GET', 'https://jsonplaceholder.typicode.com/todos/1');
requ.open('POST', 'http://127.0.0.1:8765', true);
requ.onreadystatechange = function() {
    var output = JSON.parse(requ.responseText);
    console.log(output);
    // console.log(requ.responseText);
};
var input2 = {
    "action": "addNote",
    "version": 6,
    "params": {
        "note": {
            "deckName": "Default",
            "modelName": "Einfach-26e94",
            "fields": {
                "Front": "front content",
                "Back": "back content"
            },
            "options": {
                "allowDuplicated": false
            },
            "tags": ["apitest"],
            "audio" : {}
        }
    }
};

var input3 =
    {
        "action": "addNote",
        "version": 6,
        "params": {
            "note": {
                "deckName": "Default",
                "modelName": "Basic",
                "fields": {
                    "Front": "front content",
                    "Back": "back content"
                },
                "options": {
                    "allowDuplicate": false
                },
                "tags": [
                    "yomichan"
                ],
                "audio": {
                    "url": "https://assets.languagepod101.com/dictionary/japanese/audiomp3.php?kanji=猫&kana=ねこ",
                    "filename": "yomichan_ねこ_猫.mp3",
                    "skipHash": "7e2c2f954ef6051373ba916f000168dc",
                    "fields": [
                        "Front"
                    ]
                }
            }
        }
    };


var input4 = {
"action": "addNotes",
"version": 6,
"params": {
  "notes": [ 
    {
      "deckName": "TestDeck",
      "modelName": "Basic",
      "fields": {
        "Front": "why?",
        "Back": "because!",
        "Card ID": "foo"
      },
      "options": {
        "allowDuplicate": true
      },
      "tags": []
    } 
  ]
}
}


var input = {
    "action": "modelFieldNames",
    "version": 6,
    "params": {
        "modelName": "Einfach-26e94"
    }
};
requ.send(JSON.stringify(input4));
console.log();

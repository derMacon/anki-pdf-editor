# Anki-Pdf-Editor
Commandline tool to create anki flash cards via the vim editor. Once started the programm will display a selected pdf document in which the user can navigate throughout vim itself. If a anki-card should contain a specific pdf page of the displayed document on either the front- or the backside of a note it can be passed in a simplyfied version where the pagenumber is written between tags. 

All features can be used via shortcuts. For that the program opens a costum [.vimrc](./editor/src/main/resources/com/dermacon/ankipdfeditor/.vimrc). For further information see the [manual](./otherDocs/manual-tex/manual.pdf).

## Shortcut overview

### Programm specific
* `z` / `Z`: turn next / previous page; Copy the current page tag to the default register (accessed via `p`)
* `]`: Append new card template to anki file
* `[`: paste the current page tag to cursor position
* *tab* / *shift* + *tab*: tab between fields

### vim default 
![vim-cheat-sheet](./otherDocs/manual-tex/img/vim-cheat-sheet.jpg)

## Requirements
* Ankidroid 2.1 (or newer)
* AnkiConnect [addon](https://ankiweb.net/shared/info/2055492159)
* gnome-terminal ([terminal emulation](https://askubuntu.com/questions/684180/how-to-reinstall-gnome-terminal))
* unix os (Vim)

## Usage
* Download jar from release tab and execute via `java -jar ./path/to/jar`
* Recommended: execute in the same directory to avoid initializing the project directories any where they should not be. 

### Top-level menu
```
type
  - a to write new cards
  - e to edit project properties
  - w to push to anki connect
  - q to quit without pushing
  - wq to push and exit
  - x to export a specified deck
  ------------------------
input: 
```

### Workflow example
![addNewCard](./otherDocs/instructional-gifs/addCard.gif)

### Todo
* update manual / Readme
* new cards should be appended to stack not prepended
* anki gui - fix html 
* update instructional gif
* export option
    - pdf: generate beamer file -> use pandoc for pdf generation
    - html: can be used on a simple github pages website
* deactivate debug when pushing to api
* checkstyle

### Reminder
* anki api documentation: [anki-connect](https://foosoft.net/projects/anki-connect/)
* [Blocking queue example](https://www.mkyong.com/java/java-blockingqueue-examples/)
* record screen - `peek`
* stop screenkey - `pkill -f screenkey`
* [session specfic .vimrc](https://superuser.com/questions/489930/using-a-session-specific-vimrc)
* [vim-cheat-sheet](https://www.slideshare.net/alfrescoqa/vivimcheatsheetpdf)



{"result": [{"noteId": 1571308945631, "tags": ["Betriebssysteme::Einf\u00fchrung"], "fields": {"Front": {"value": "<div>Charakterisieren Sie das BIOS und nennen Sie die wichtigsten Aufgaben. Wofuer steht die Abkuerzung ueberhaupt?</div>", "order": 0}, "Back": {"value": "<div><img src=betriebssysteme_skript_12.png></div>", "order": 1}}, "modelName": "Basic", "cards": [1571308945633]}], "error": null}


fields": {"Front": {"value": "<div></div>"}}

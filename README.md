# Anki-Pdf-Editor
Commandline tool to create anki flash cards via the vim editor. Once started the programm will display a selected pdf document in which the user can navigate throughout vim itself. If a anki-card should contain a specific pdf page of the displayed document on either the front- or the backside of a note it can be passed in a simplyfied version where the pagenumber is written between tags. 

All features can be used via shortcuts. For that the program opens a costum [.vimrc](./editor/src/main/resources/com/dermacon/ankipdfeditor/.vimrc). For further information see the [manual](./otherDocs/manual-tex/manual.pdf).

## Shortcut overview

### Programm specific
* `z` / `Z`: turn next / previous page; Copy the current page tag to the default register (accessed via `p`)
* `]`: Append new card template to anki file
* `[`: paste the current page tag to cursor position
* *tab* / *shift* + *tab*: tab between fields
* `strg` + `b`: bold html tags
* `strg` + `u`: underlined html tags
* `strg` + `k`: cursiv html tags

### vim default 
![vim-cheat-sheet](./otherDocs/manual-tex/img/vim-cheat-sheet.jpg)

## Requirements
* Ankidroid 2.1 (or newer)
* AnkiConnect [addon](https://ankiweb.net/shared/info/2055492159)
* gnome-terminal ([terminal emulation](https://askubuntu.com/questions/684180/how-to-reinstall-gnome-terminal))
* unix os (Vim)

## Usage
* Download jar from release tab and execute via `java -jar ./path/to/jar`
* Recommended: execute in the same directory as the `.jar` to avoid initializing the project directories anywhere they should not be. 

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
* vim - append new card -> save current file in case it crashes
* dont switch into insert mode when adding new image
* dont switch into insert mode when tabbing between fields
* multiple tags on one card not working at all (seen as one in browser)
* go into normal mode when tabbing
* page not set after adding new card
* pdf should be referenced directly without copying to the lastdocs dir
* stay in edit mode after inserting img
* quote german umlaute in tags
* htlm listen as shortcut in vimrc
* change currpage in projhistory when editing properties
* add mechanism to add external images
* delete linefeed after image tag
* SZ in Umlaute aufnehmen
* stay in normal mode when creating a new card.
* page tag in insert mode not working
* shutdown when target pdf not found -> just use standard manual pdf
* why does the projectinfo component hold a current image instance.
* html export copy img from anki media
* CSS image tag has to overlay (left adjust, should be centered), add padding top / bottom
* html export after adding card not working, maybe sleep after sending cards to api...
* export option
    - pdf: generate beamer file -> use pandoc for pdf generation
    - html: can be used on a simple github pages website
* implement project history selection
* update manual / Readme (especially menu preview)
* anki gui - fix html 
* update instructional gif
* checkstyle

### Reminder
* anki api documentation: [anki-connect](https://foosoft.net/projects/anki-connect/)
* [Blocking queue example](https://www.mkyong.com/java/java-blockingqueue-examples/)
* record screen - `peek`
* stop screenkey - `pkill -f screenkey`
* [session specfic .vimrc](https://superuser.com/questions/489930/using-a-session-specific-vimrc)
* [vim-cheat-sheet](https://www.slideshare.net/alfrescoqa/vivimcheatsheetpdf)
* kill anki: `ps aux | grep anki` => `kill <pid>`


# Anki-Pdf-Editor
Commandline tool to create anki flash cards via the vim editor. Once started the programm will display a selected pdf document in which the user can navigate throughout vim itself. If a anki-card should contain a specific pdf page of the displayed document on either the front- or the backside of a note it can be passed in a simplyfied version where the pagenumber is written between tags. 

All features can also be used via shortcuts. To use this programm the user has to paste the configuration of the given [.vimrc](./config/vim-shortcuts.txt) into his own and start the prebuild `.jar` file which is available in the release tab of this repository.

## Requirements
* Ankidroid 2.1 (or newer)
* AnkiConnect [addon](https://ankiweb.net/shared/info/2055492159)
* gnome-terminal ([terminal emulation](https://askubuntu.com/questions/684180/how-to-reinstall-gnome-terminal))
* unix os

## Usage
* download jar from release tab and execute via `java -jar ./path/to/jar`

### Top-level menu
```
type
  - a to write new cards
  - e to edit project properties
  - w to push to anki connect
  - q to quit without pushing
  - wq to push and exit
  ------------------------
input: 
```

### Create new Card
![addNewCard](./otherDocs/instructional-gifs/addNewCard.gif)

### Tab fields
![tabFields](./otherDocs/instructional-gifs/tabFields.gif)

### Edit properties
// todo insert gif



### Resources
* anki api documentation: [anki-connect](https://foosoft.net/projects/anki-connect/)
* [Blocking queue example](https://www.mkyong.com/java/java-blockingqueue-examples/)
* record screen - `peek`
* stop screenkey - `pkill -f screenkey`
* [session specfic .vimrc](https://superuser.com/questions/489930/using-a-session-specific-vimrc)

### Todo
* update vimrc with 
    - page tag instead of page num
    - curr page with `,p`
* udpate manual
* make instructional gifs for readme 
* checkstyle


# Anki-Pdf-Editor
Commandline tool to create anki flash cards via the vim editor. Once started the programm will display a selected pdf document in which the user can navigate throughout vim itself. If a anki-card should contain a specific pdf page of the displayed document on either the front- or the backside of a note it can be passed in a simplyfied version where the pagenumber is written between tags. 

All features can be used via shortcuts. For that the program opens a costum [.vimrc](./config/vim-shortcuts.txt). For further information see the [manual](./otherDocs/manual-tex/manual.pdf).

## Shortcut overview

### Programm specific
\item `z` / `Z`: turn next / previous page; Copy the current page tag to the default register (accessed via \emph{p})
\item`,c`: Append new card template to anki file
\item `,p`: Reload page tag, pastes the current page tag to cursor position
\item `,t` / `,T`: tab between fields

### vim default 
![vim-cheat-sheet](./otherDocs/manual-tex/img/vim-cheat-sheet.jpg)

## Requirements
* Ankidroid 2.1 (or newer)
* AnkiConnect [addon](https://ankiweb.net/shared/info/2055492159)
* gnome-terminal ([terminal emulation](https://askubuntu.com/questions/684180/how-to-reinstall-gnome-terminal))
* unix os (Vim)

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

### Insert page
// todo insert gif

### Tab fields
   ![tabFields](./otherDocs/instructional-gifs/tabFields.gif)

### Edit properties
// todo insert gif

### Todo
* backend should render line with div tags
* update vimrc with 
    - page tag instead of page num
    - curr page with `,p`
* make instructional gifs for readme 
* publish build
* checkstyle

### Reminder
* anki api documentation: [anki-connect](https://foosoft.net/projects/anki-connect/)
* [Blocking queue example](https://www.mkyong.com/java/java-blockingqueue-examples/)
* record screen - `peek`
* stop screenkey - `pkill -f screenkey`
* [session specfic .vimrc](https://superuser.com/questions/489930/using-a-session-specific-vimrc)
* [vim-cheat-sheet](https://www.slideshare.net/alfrescoqa/vivimcheatsheetpdf)


# Anki-Pdf-Editor
This webapp displays a pdf-reader containing an editor to create digital index cards. These cards can then be reviewed with the open source program (ankidroid)[https://apps.ankiweb.net/]. The whole purpose of this project was to design a more efficient workflow for creating those cards, since the editor heavily relies on shortcuts. It also provides a button / shortcut to directly copy the current page displayed in the pdf-viewer to the card. 


## npm installation
To install the Hotykey-Lib in local npm use `npm install react-hotkeys --save`, for the react-pdf package `npm install react-pdf`.


## Projectstructure
Two main projects: 
* `anki-pdf`: Front-end react project displaying the editor components. Server runs on `http://localhost:3000/`.
* `anki-api-connector`: Spring boot rest api as the backend for the webapp. Server runs on `http://localhost:8080/`.

## Todo - Front-end
### Near-term
* implement page insert at cursor pos
* implement menu
* fix responsive css

### Long-term
* implement pdf display
* implement pdf search
* make components resizable

### Todo - Back-end
* plan the whole thing


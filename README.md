# Anki-Pdf-Editor
This webapp displays a pdf-reader containing an editor to create digital index cards. These cards can then be reviewed with the open source program [ankidroid](https://apps.ankiweb.net/). The whole purpose of this project was to design a more efficient workflow for creating those cards, since it always took quite some time to screenshot the required parts of the pdf document. To solve this issue I implemented a button / shortcut to directly copy the current page displayed in the pdf-viewer to the card. 


## npm installation
To install the Hotykey-Lib in local npm use `npm install react-hotkeys --save`, for the react-pdf package `npm install react-pdf`.

// Helmet: `npm install --save react-helmet`
Webpacks: `npm install --save-dev webpack`


## Project structure
Two main projects: 
* `anki-pdf`: Front-end react project displaying the editor components. Server runs on `http://localhost:3000/`.
* `anki-api-connector`: Spring boot rest api as the back-end for the webapp. Server runs on `http://localhost:8080/`.

## Todo - Front-end
### near-term
* implement page insert at cursor pos
* implement menu
* fix responsive css

### long-term
* implement pdf display
* implement pdf search
* make components resizable
* fix: when error alert -> tags-field loses value

### Todo - Back-end
* plan the whole thing

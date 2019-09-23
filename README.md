# Anki-Pdf-Editor
This webapp displays a pdf-reader containing an editor to create digital index cards. These cards can then be reviewed with the open source program [ankidroid](https://apps.ankiweb.net/). The whole purpose of this project was to design a more efficient workflow for creating those cards, since it always took quite some time to screenshot the required parts of the pdf document. To solve this issue I implemented a button / shortcut to directly copy the current page displayed in the pdf-viewer to the card. 

## Project structure
Two main projects: 
* `anki-pdf-editor`: Maven project containing a spring boot project as the backend and a react project as a frontend running on port 8080 and 3000 respectively.
* `lastDocs`: Contains the last pdf documents which the user has selected.

## Screenshot
![alt](./prototype/screenshots/2.png)

## Todo - Front-end
### near-term
* fix responsive css
* textarea should submit linebreaks

### long-term
* implement pdf search
* make components resizable

### Resources
* [Color palette for the themes](https://material.io/design/color/dark-theme.html#behavior)
* Guide - [Bind React with Spring](https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot)
* Example Project - [Binding Spring / React](https://github.com/tzehe/react-spring-example)


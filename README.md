# Anki-Pdf-Editor
This webapp displays a pdf-reader containing an editor to create digital index cards. These cards can then be reviewed with the open source program [ankidroid](https://apps.ankiweb.net/). The whole purpose of this project was to design a more efficient workflow for creating those cards, since it always took quite some time to screenshot the required parts of the pdf document. To solve this issue I implemented a button / shortcut to directly copy the current page displayed in the pdf-viewer to the card. 

For further information see the [manual](lastDocs/manual.pdf)

## Project structure
Two main projects: 
* `anki-pdf`: Front-end react project displaying the editor components. Server runs on `http://localhost:3000/`.
* `anki-api-connector`: Spring boot rest api as the back-end for the webapp. Server runs on `http://localhost:8080/`.

## Screenshot
![alt](./screenshots/2.png)

## Todo - Front-end
### near-term
* implement menu
* fix responsive css
* textarea should submit linebreaks

### long-term
* implement pdf search
* make components resizable

### Resources
* [Color palette for the themes](https://material.io/design/color/dark-theme.html#behavior)
* [Bind React with Spring](https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot)
* Example - [Binding Spring / React](https://github.com/tzehe/react-spring-example)

* Might be [usefull](https://stackoverflow.com/questions/46334733/spring-data-rest-application-backend-notify-frontend)
* Websocket - [Stackoverflow](https://stackoverflow.com/questions/37307697/scheduled-websocket-push-with-springboot)


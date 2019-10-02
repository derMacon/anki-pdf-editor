package com.dermacon.data.project;

import com.dermacon.ankiApi.PostConnector;
import com.dermacon.ankiApi.request.AddNoteAnkiRequest;
import com.dermacon.data.card.Card;
import com.dermacon.data.card.CardStackFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProjectController {
    private static final int ANKI_BOOT_TIME = 2500;
    private static final String API_URL = "http://localhost:8080/";
    private static final String PROJECT_INFO_COMMAND = API_URL + "getProjectInfo";
    private static final String NEW_DECK_COMMAND = API_URL + "selectDeck";
    private static final String NEW_PDF_COMMAND = API_URL + "selectNewPdf";
    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";
    // opens a node server on port 9000
    private static final String OPEN_PDF_VIEWER_COMMAND = "cd %s && http-server -p 9000";
    private static final String PDF_SERVER_URL = "http://127.0.0.1:9000/";
    private static final String ANKI_START_COMMAND = "anki";

    private static final int SPRING_API_PORT = 8080;
    private static final int ANKI_API_PORT = 8765;

    private DataContainer dataContainer;

    public ProjectController() throws IOException {
        this.dataContainer = new DataContainer();
    }

    public DataContainer getDataContainer() {
        return dataContainer;
    }


    public void pushToAnki() throws IOException {
        startAnki();
        List<Card> cardStack = CardStackFactory.produceStack(dataContainer.getProjectInfo());
        // not possible as stream since an exception will be thrown
        // for some reason the postconnector instance is not reusable...
        for(Card curr : cardStack) {
            new PostConnector(ANKI_API_PORT).jsonRequest(new AddNoteAnkiRequest(curr));
        }

    }

    private static void startAnki() throws IOException {
        Runtime.getRuntime().exec(ANKI_START_COMMAND);
        try {
            Thread.sleep(ANKI_BOOT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

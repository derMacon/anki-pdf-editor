package com.dermacon.ankipdfeditor.data.project;

import com.dermacon.ankipdfeditor.ankiApi.PostConnector;
import com.dermacon.ankipdfeditor.ankiApi.request.AddNoteAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.AnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.GetDecksAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.SyncAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.response.AnkiResponse;
import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.card.CardStackFactory;
import com.dermacon.ankipdfeditor.data.card.IncompleteCardException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class AnkiConnector {
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


    public static String[] getPossibleDecks() throws IOException {
        startAnki();
        PostConnector connector = new PostConnector(8765);
        AnkiResponse r = connector.jsonRequest(new GetDecksAnkiRequest());
        List<String> out = (ArrayList<String>) r.getResult();
        return out.toArray(new String[0]);
    }

    public static void pushToAnki(ProjectInfo projectInfo) throws IOException, IncompleteCardException {
        startAnki();
        List<Card> cardStack = new CardStackFactory(projectInfo).produceStack();
        // not possible as stream since an exception will be thrown
        // for some reason the postconnector instance is not reusable...
        AnkiResponse response;
        List<Card> problematicCards = new LinkedList<>();
        for(Card curr : cardStack) {
            AnkiRequest request = new AddNoteAnkiRequest(curr);
            System.out.println("card: " + curr);
            System.out.println(request.toJson());
            response = new PostConnector(ANKI_API_PORT).jsonRequest(new AddNoteAnkiRequest(curr));
            if (response.getError() != null) {
                problematicCards.add(curr);
            }
        }

        if (problematicCards.isEmpty()) {
            response = new PostConnector(ANKI_API_PORT).jsonRequest(new SyncAnkiRequest());
            if (response.getError() != null) {
                throw new IOException("could not sync with anki api");
            }
        } else {
            throw new IncompleteCardException(
                    "No sync with anki possible - the following cards were incorrect:\n"
                            + problematicCards.toString()
            );
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

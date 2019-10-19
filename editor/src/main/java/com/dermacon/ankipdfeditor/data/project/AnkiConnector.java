package com.dermacon.ankipdfeditor.data.project;

import com.dermacon.ankipdfeditor.ankiApi.PostConnector;
import com.dermacon.ankipdfeditor.ankiApi.request.consumer.AddNoteAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.consumer.ConsumingRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.consumer.CreateDeckAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.consumer.SyncAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.function.FindNotesRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.function.GetDecksAnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.request.function.NotesInfoRequest;
import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;
import com.dermacon.ankipdfeditor.ankiApi.response.function.IDLstReply;
import com.dermacon.ankipdfeditor.ankiApi.response.function.NameLstReply;
import com.dermacon.ankipdfeditor.ankiApi.response.function.NotesInfoReply;
import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.card.CardStackBuilder;
import com.dermacon.ankipdfeditor.data.card.CardStackFileFactory;
import com.dermacon.ankipdfeditor.data.card.IncompleteSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Connection to the anki connect api
 */
public class AnkiConnector {

    // todo check which constant are actually needed
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

    /**
     * Returns the names of all decks which the user has created and reside in the
     * current anki repository.
     * @return names of all current decks.
     * @throws IOException thrown when the connection is nnt working as intended
     */
    public static String[] getDeckNames() throws IOException {
        startAnki();
        PostConnector connector = new PostConnector(ANKI_API_PORT);
        NameLstReply r = connector.jsonRequest(new GetDecksAnkiRequest());
        return r.getResult();
    }

    /**
     * Extracts a card array from the given deck name.
     * 1. Get the note ID from each card in the deck
     * 2. Get the note info using the note ID
     * @param deckname name of deck from which the cards should be extracted
     * @return card collection from the deck
     * @throws IOException thrown when the connection is nnt working as intended
     */
    public static List<Card> getCardsFromDeck(String deckname) throws IOException {
        FindNotesRequest request = new FindNotesRequest(deckname);
        IDLstReply reply = new PostConnector(ANKI_API_PORT).jsonRequest(request);

        CardStackBuilder builder = new CardStackBuilder();
        for(Long currId : reply.getResult()) {
            // todo
            NotesInfoReply response = new PostConnector(ANKI_API_PORT)
                    .jsonRequest(new NotesInfoRequest(currId));
//            String json = ((ArrayList<String>)response).get(0);
//            builder.addCard(json);
            System.out.println(currId);
        }

        return builder.getStack();
    }

    /**
     * Reads the current project info components and pushes the specified
     * file to the anki connect api.
     * Steps:
     * 1. Starts an anki instance on the users desktop
     * 2. Creates a new deck with a corresponding name if it wasn't created in an
     * earlier attempt.
     * 3. Create the card collection that sould be pushed
     * 4. Push the collection to the api
     *
     * @param projectInfo project info compoonent containing the deck name and .anki file location
     * @throws IOException thrown when the connection is nnt working as intended
     * @throws IncompleteSyntaxException thrown when a card in the .anki file does not follow the
     * specified syntax.
     */
    public static void pushToAnki(ProjectInfo projectInfo) throws IOException, IncompleteSyntaxException {
        startAnki();

        // create deck if necessary
        String deckname = projectInfo.getDeck().getName();
        if (!deckExists(deckname)) {
            createDeck(deckname);
        }

        // generate cards that should be pushed
        List<Card> cardStack = new CardStackFileFactory(projectInfo).produceStack();
        pushCard(cardStack);
    }

    /**
     * Checks whether a deck exists.
     * @param deckname name of the deck
     * @return true if the deck already exists in the users anki repository
     */
    private static boolean deckExists(String deckname) {
        try {
            String[] res = getDeckNames();
            return Arrays.stream(res).anyMatch(deckname::equals);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * todo maybe add a syncrequest... be consistent with pushCards-func
     * Creates an anki deck with the given name. Deck can be viewed through
     * the anki app itself.
     * @param deckname name of the deck that should be created.
     * @throws IOException thrown when the connection is nnt working as intended
     */
    private static void createDeck(String deckname) throws IOException {
        PostConnector connector = new PostConnector(ANKI_API_PORT);
        ConsumingRequest request = new CreateDeckAnkiRequest(deckname);
        AnkiReply response = connector.jsonRequest(request);

        if (response.getError() != null) {
            throw new IOException("Cannot create deck with name: " + deckname);
        }
    }

    /**
     * Pushes a given card collection to the respective deck. These cards can be
     * viewed through the anki app itself.
     * The function also syncs with the global anki repository so all devices can
     * view the newly pushed cards.
     * @param cardStack cards to push to anki.
     * @throws IOException thrown when the connection is nnt working as intended
     */
    private static void pushCard(List<Card> cardStack) throws IOException {
        // not possible as stream since an exception will be thrown
        // for some reason the postconnector instance is not reusable...
        AnkiReply response;
        List<Card> problematicCards = new LinkedList<>();
        for(Card curr : cardStack) {
            ConsumingRequest request = new AddNoteAnkiRequest(curr);
            // todo delete debug
            System.out.println("card: " + curr);
            System.out.println(request.toJson());

            response = new PostConnector(ANKI_API_PORT)
                    .jsonRequest(request);
            if (response.getError() != null) {
                problematicCards.add(curr);
            }
        }

        if (problematicCards.isEmpty()) {
            response = new PostConnector(ANKI_API_PORT)
                    .jsonRequest(new SyncAnkiRequest());
            if (response.getError() != null) {
                throw new IOException("could not sync with anki api");
            }
        } else {
            throw new IncompleteSyntaxException(
                    "No sync with anki possible - the following cards were incorrect:\n"
                            + problematicCards.toString()
            );
        }

    }

    /**
     * Starts the currently installed version on the users system.
     * @throws IOException thrown when the connection is nnt working as intended
     */
    private static void startAnki() throws IOException {
        Runtime.getRuntime().exec(ANKI_START_COMMAND);
        try {
            Thread.sleep(ANKI_BOOT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

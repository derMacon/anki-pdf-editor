package com.dermacon.ankipdfeditor.apiController;

import com.dermacon.ankipdfeditor.ankiRequest.PostConnector;
import com.dermacon.ankipdfeditor.ankiRequest.request.GetDecksRequest;
import com.dermacon.ankipdfeditor.ankiRequest.response.Response;
import com.dermacon.ankipdfeditor.logic.Card;
import com.dermacon.ankipdfeditor.logic.HtmlParser;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiController implements ApiConnection {

    private HtmlParser parser;
    private ProjectInfo projectInfo;

    public ApiController() throws IOException {
//        projectInfo = new ProjectInfo();
//        parser = new HtmlParser(projectInfo.getPdfPath());
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/addCard", produces = APPLICATION_JSON_VALUE)
    public String addCard(Card card) {
        System.out.println("card: ");
        System.out.println(card);
        // todo ueberarbeiten
//        try {
//            PostConnector connector = new PostConnector(8765);
//            connector.jsonRequest(new AddNoteRequest(parser.parseImgTag(card)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "success";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/setPageNum")
    public void setPageNum(Integer pageNum) {
        System.out.println("new page: " + pageNum);
//        this.projectInfo.updatePage(pageNum);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/waitForChange")
    public int waitForChange() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.projectInfo.getCurrPageNum();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/parseAnkiFile")
    public void parseAnkiFile(String path) {

    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/selectedPdfName")
    public String getSelectedPdfName() {
        return this.projectInfo.getPdfName();
    }

    /**
     * https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc
     *
     * @return
     * @throws IOException
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/serveSelectedPdf", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> serveSelectedPdf() throws IOException {
        // todo ueberarbeiten
        String path = projectInfo.getPdfPath();
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        byte[] contents = null;

        contents = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);

        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/selectNewPdf")
    public String selectNewPdf() throws IOException {
        String message = "success";

        File output = openFileChooser();
        String outName = output.getName();
        File copy = new File(projectInfo.getLastDocsDir() + outName);

        // only copy if file isn't already existent in the lastDocs directory
        if (output != null && output.exists() && !copy.exists()) {
            System.out.println("copy " + output.getAbsolutePath() + " to " + copy.getAbsolutePath());
            FileUtils.copyFile(output, copy);
        }

        String deck = projectInfo.getDeckFile();
        projectInfo = new ProjectInfo(deck, output.getName());

        System.out.println("pi: " + projectInfo.toJson());

        return message;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getProjectInfo")
    public ProjectInfo getProjectInfo() throws IOException {
        return this.projectInfo;
    }

    private File openFileChooser() {
        JFileChooser chooser = new JFileChooser(projectInfo.getLastDocsDir());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF", "pdf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        File output = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            output = chooser.getSelectedFile();
        }
        return output;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/selectDeck")
    public void selectDeck() {
        List<String> out = null;

        System.out.println("kommt anasdfasdf");

        try {
            PostConnector connector = new PostConnector(8765);
            Response r = connector.jsonRequest(new GetDecksRequest());
            out = (ArrayList<String>) r.getResult();

            chooseDeck(out.toArray(new String[0]));

        } catch (IOException e) {
            System.out.println("hmmm " + e.getMessage());
            e.printStackTrace();
        }
    }

    // todo getter for projectinfo => no need getDeck and getPdf / getPdfName etc.

    private void chooseDeck(String[] decks) {
        Consumer<String> callback = deck -> updateDeck(deck);
        DeckChooser deckChooser = new DeckChooser(decks, callback);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deckChooser.createAndShowGUI(decks, callback);
            }
        });
    }

    private void updateDeck(String newDeck) {
        assert newDeck != newDeck;
        String pdf = this.projectInfo.getPdfPath();
        this.projectInfo = new ProjectInfo(newDeck, pdf);
        // todo write to file
        System.out.println("updated projInfo: " + projectInfo.toJson());
        // todo make anki file for vim
    }

    @RequestMapping(method = RequestMethod.POST, value = "/setCurrPage")
    public void setCurrPage(int pageNum) {
        projectInfo.updatePage(pageNum);
        System.out.println(projectInfo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCurrPage")
    public String getCurrPage() {
        return "<" + this.projectInfo.getCurrPageNum() + ">";
    }

}


/*
    // todo check if needed
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/retrievePdf", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> retrievePdf(String name) {

        // https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc

        File file = new File("src/main/resources/META-INF/resources/example.pdf");
        System.out.println(file.getAbsolutePath());
        System.out.println("file: " + (file.exists() && !file.isDirectory()));
        byte[] contents = null;

        try {
            contents = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("not cool\n" + e.getMessage());
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);

        System.out.println("file sent");
        return response;
    }


    private ResponseEntity<byte[]> servePdfFile(File file) {
        // https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc

        // todo delete debug - schick machen
        System.out.println(file.getAbsolutePath());
        System.out.println("file: " + (file.exists() && !file.isDirectory()));
        byte[] contents = null;

        try {
            contents = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("not cool\n" + e.getMessage());
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);

        System.out.println("file sent");
        return response;

    }


 */

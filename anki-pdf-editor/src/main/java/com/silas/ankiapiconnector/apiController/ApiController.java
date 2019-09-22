package com.silas.ankiapiconnector.apiController;

import com.silas.ankiapiconnector.ankiRequest.PostConnector;
import com.silas.ankiapiconnector.ankiRequest.request.AddNoteRequest;
import com.silas.ankiapiconnector.ankiRequest.request.GetDecksRequest;
import com.silas.ankiapiconnector.ankiRequest.response.Response;
import com.silas.ankiapiconnector.apiController.projectInfo.ProjectInfo;
import com.silas.ankiapiconnector.logic.Card;
import com.silas.ankiapiconnector.logic.HtmlParser;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiController implements ApiConnection {

    private HtmlParser parser;
    private ProjectInfo projectInfo;

    public ApiController() throws IOException {
        projectInfo = new ProjectInfo();
        parser = new HtmlParser(projectInfo.getPdfPath());
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @RequestMapping(method = RequestMethod.POST, value = "/initProjectInfo")
//    public void initProjectInfo(ProjectInfo projectInfo) throws IOException {
//        System.out.println("kommt an: " + projectInfo.toJson());
////        this.projectInfo = projectInfo;
////        this.parser = new HtmlParser(projectInfo.getPdf());
//    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/addCard", produces = APPLICATION_JSON_VALUE)
    public String addCard(Card card) {
        System.out.println("card: ");
        System.out.println(card);
        // todo ueberarbeiten
        try {
            PostConnector connector = new PostConnector(8765);
            connector.jsonRequest(new AddNoteRequest(parser.parseImgTag(card)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
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

        String deck = projectInfo.getDeck();
        projectInfo = new ProjectInfo(deck, output.getName());

        System.out.println("pi: " + projectInfo.toJson());

        return message;
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

            String deck = chooseDeck(out.toArray(new String[0]));

        } catch (IOException e) {
            System.out.println("hmmm " + e.getMessage());
            e.printStackTrace();
        }
    }

    // todo getter for projectinfo => no need getDeck and getPdf / getPdfName etc.

    private String chooseDeck(String[] decks) {
        String output = null;

        DeckChooser deckChooser = new DeckChooser(decks);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deckChooser.createAndShowGUI(decks);
            }
        });

        return output;
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

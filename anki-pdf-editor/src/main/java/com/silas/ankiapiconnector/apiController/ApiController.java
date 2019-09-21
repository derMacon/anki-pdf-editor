package com.silas.ankiapiconnector.apiController;
import com.silas.ankiapiconnector.ankiRequest.AnkiConnector;
import com.silas.ankiapiconnector.ankiRequest.request.AddNoteRequest;
import com.silas.ankiapiconnector.ankiRequest.request.GetDecksRequest;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;
import com.silas.ankiapiconnector.logic.Card;
import com.silas.ankiapiconnector.logic.HtmlParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ApiController implements ApiConnection {

    private HtmlParser parser;

    public ApiController() throws IOException {
        try {
//            String url_resLastDoc = "/src/main/resources/META-INF/resources/lastDocs/";
            String url_resLastDoc = "/home/silasUser/Documents/projects/codecademy_revenue_reactExample/lastDocs/";
            String url_resTempPages = "/src/main/resources/META-INF/resources/tempPages/pdf/";
//            String url = System.getProperty("user.dir") + url_resLastDoc + "CVL.pdf";
            String url = url_resLastDoc + "CVL.pdf";
            parser = new HtmlParser(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/addCard", produces = APPLICATION_JSON_VALUE)
    public String addCard(Card card) {
        System.out.println("card: ");
        System.out.println(card);
        // todo ueberarbeiten
        try {
            AnkiConnector connector = new AnkiConnector();
            connector.request(new AddNoteRequest(parser.parseImgTag(card)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/selectedPdfName")
    public String getSelectedPdfName() {
        return this.parser.getPdfName();
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/serveSelectedPdf", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> serveSelectedPdf() {

        // https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc

//        File file = new File("src/main/resources/META-INF/resources/lastDocs/CVL.pdf");
        File file = new File(System.getProperty("user.home") + "/Documents/lastDocs/CVL.pdf");
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

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/selectNewPdf")
    public String selectNewPdf(String path) {
        String message = "success";
        // todo
        System.out.println(path);

        return message;
    }

    @Override
    public String openNewProject(String name) {
        return null;
    }

    @Override
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/showProjects")
    public List<String> showPossibleProjects() {
        List<String> out = null;

        try {
            AnkiConnector connector = new AnkiConnector();
            Response r = connector.request(new GetDecksRequest());
            out = (ArrayList<String>)r.getResult();
        } catch (IOException e) {
            System.out.println("hmmm " + e.getMessage());
            e.printStackTrace();
        }

        return out;
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

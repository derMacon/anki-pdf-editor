package com.silas.ankiapiconnector.apiController;


import com.silas.ankiapiconnector.ankiRequest.AnkiConnector;
import com.silas.ankiapiconnector.ankiRequest.request.AddNoteRequest;
import com.silas.ankiapiconnector.logic.Card;
import com.silas.ankiapiconnector.logic.Document;
import com.silas.ankiapiconnector.logic.PdfDoc;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ApiController implements ApiConnection {

    private Document doc;

    public ApiController() {
        try {
            String url_resLastDoc = "/src/main/resources/META-INF/resources/lastDocs/";
            String url_resTempPages = "/src/main/resources/META-INF/resources/tempPages/pdf/";
            String url = System.getProperty("user.dir") + url_resLastDoc + "CVL.pdf";
            doc = new PdfDoc(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCard", produces = APPLICATION_JSON_VALUE)
    public String addCard(Card card) {
        System.out.println("card: ");
        System.out.println(card);
        // todo ueberarbeiten
        try {
            AnkiConnector connector = new AnkiConnector();
            connector.request(new AddNoteRequest(card));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

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


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/serveSelectedPdf", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> serveSelectedPdf(String name) {

        // https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc

        File file = new File("src/main/resources/META-INF/resources/lastDocs/CVL.pdf");
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

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/turnNextPage")
    public void turnNextPage() {
        assert doc != null;
        doc.turnNextPage();
        System.out.println("kommt an" + doc.getCurrentPageNum());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getNextPage")
    public String getNextPage() {
        assert doc != null;
        return this.doc.getNextPage_url();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCurrPage")
    public String getCurrPage() {
        assert doc != null;
        return this.doc.getCurrPage_url();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPrevPage")
    public String getPrevPage() {
        assert doc != null;
        return this.doc.getPrevPage_url();
    }

    @Override
    public void turnPrevPage() {
        assert doc != null;
        doc.turnPrevPage();
    }

    @Override
    public String openNewDocument(String path) {
        String message = "success";
        try {
            this.doc = new PdfDoc(path);
        } catch (IOException e) {
            message = "file non existent";
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String openNewProject(String name) {
        return null;
    }
}

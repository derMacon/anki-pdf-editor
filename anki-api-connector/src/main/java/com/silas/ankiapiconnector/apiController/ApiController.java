package com.silas.ankiapiconnector.apiController;


import com.silas.ankiapiconnector.logic.Card;
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
public class ApiController {

    @RequestMapping(method = RequestMethod.POST, value = "/addCard", produces = APPLICATION_JSON_VALUE)
    public String register(Card card) {
        System.out.println("card: ");
        System.out.println(card);
        return "success";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/retrievePdf", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> retrievePdf() {

        // https://stackoverflow.com/questions/16652760/return-generated-pdf-using-spring-mvc

        System.out.println("hiheyo");
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

}

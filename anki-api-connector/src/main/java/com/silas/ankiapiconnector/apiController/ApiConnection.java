package com.silas.ankiapiconnector.apiController;

import com.silas.ankiapiconnector.logic.Card;
import org.springframework.http.ResponseEntity;

public interface ApiConnection {

    // post requests
    String addCard(Card card);
    String openNewDocument(String path);
    String openNewProject(String name);

    // get requests
    ResponseEntity<byte[]> serveSelectedPdf();
    String getSelectedPdfName();

}

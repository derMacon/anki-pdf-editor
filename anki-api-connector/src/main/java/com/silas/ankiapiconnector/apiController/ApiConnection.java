package com.silas.ankiapiconnector.apiController;

import com.silas.ankiapiconnector.logic.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiConnection {

    // post requests
    String addCard(Card card);
    String openNewDocument();
    String openNewProject(String name);
    List<String> showPossibleProjects();

    // get requests
    ResponseEntity<byte[]> serveSelectedPdf();
    String getSelectedPdfName();

}

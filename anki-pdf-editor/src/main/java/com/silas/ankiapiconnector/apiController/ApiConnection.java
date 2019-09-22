package com.silas.ankiapiconnector.apiController;

import com.silas.ankiapiconnector.logic.Card;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ApiConnection {

    // post requests
//    void initProjectInfo(ProjectInfo projectInfo);
    String addCard(Card card);
    String selectNewPdf(String path);
    String openNewProject(String name);
    List<String> showPossibleProjects();

    // get requests
    ResponseEntity<byte[]> serveSelectedPdf() throws IOException;
    String getSelectedPdfName();

}

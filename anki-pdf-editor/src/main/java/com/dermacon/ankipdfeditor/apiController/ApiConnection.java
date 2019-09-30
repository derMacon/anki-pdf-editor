package com.dermacon.ankipdfeditor.apiController;

import com.dermacon.ankipdfeditor.logic.Card;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ApiConnection {

    // post requests
//    void initProjectInfo(ProjectInfo projectInfo);
    String addCard(Card card);

    String selectNewPdf() throws IOException;

//    String openNewProject(String name);

//    List<String> showPossibleProjects();

    // get requests
    ResponseEntity<byte[]> serveSelectedPdf() throws IOException;

    String getSelectedPdfName();

}

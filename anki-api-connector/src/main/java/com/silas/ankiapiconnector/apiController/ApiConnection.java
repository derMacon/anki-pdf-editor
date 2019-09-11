package com.silas.ankiapiconnector.apiController;

import com.silas.ankiapiconnector.logic.Card;
import org.springframework.http.ResponseEntity;

public interface ApiConnection {

    String addCard(Card card);

    void turnNextPage();
    void turnPrevPage();

    String openNewDocument(String path);
    String openNewProject(String name);

}

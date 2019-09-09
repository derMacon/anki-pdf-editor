package com.silas.ankiapiconnector.apiController;


import com.silas.ankiapiconnector.logic.Card;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}

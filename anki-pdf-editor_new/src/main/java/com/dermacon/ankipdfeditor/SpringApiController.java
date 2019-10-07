package com.dermacon.ankipdfeditor;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpringApiController {

    private static FXMLController controller;

    @RequestMapping(method = RequestMethod.GET, value = "/turnPrevPage")
    public int turnPrevPage() {
        System.out.println("jo");
        controller.turnPrevPage();
        return 42;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/turnNextPage")
    public int turnNextPage() {
        System.out.println("jojo");
        controller.turnNextPage();
        return 43;
    }

    public static void setJFXController(FXMLController contr){
        controller = contr;
    }



}

package com.dermacon.springApi;

import com.dermacon.ui.FXMLController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpringApiController {

    private static FXMLController controller;

    public static void setJFXController(FXMLController controller_) {
        controller = controller_;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/turnPrevPage")
    public int turnPrevPage() {
        return controller.turnPrevPage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/turnNextPage")
    public int turnNextPage() {
        return controller.turnNextPage();
    }

    

}

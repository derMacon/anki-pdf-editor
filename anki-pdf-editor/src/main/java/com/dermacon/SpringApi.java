package com.dermacon;

import com.dermacon.ui.PrimaryController;
import javafx.application.Platform;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpringApi {

    private static PrimaryController controller;

    public static void setJFXController(PrimaryController controller_) {
        controller = controller_;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/turnNextPage")
    public void turnNextPage() {
        controller.turnNextPage();
    }

    

}

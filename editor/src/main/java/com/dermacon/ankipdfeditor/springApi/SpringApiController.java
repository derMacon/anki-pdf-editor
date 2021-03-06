package com.dermacon.ankipdfeditor.springApi;

import com.dermacon.ankipdfeditor.ui.FXMLController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpringApiController {

    private static final String TAG_FORMAT = "<%s>";

    /**
     * Needed when an external user wants to alter the internal state (e.g.
     * pageTurn) and the system needs to delegate the information to the gui.
     */
    private static FXMLController controller;

    /**
     * Setter for the jfx controller.
     * @param controller_
     */
    public static void setJFXController(FXMLController controller_) {
        controller = controller_;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCurrPage")
    public String getCurrPage() {
        return String.format(TAG_FORMAT, controller.getCurrPage());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/turnPrevPage")
    public String turnPrevPage() {
        return String.format(TAG_FORMAT, controller.turnPrevPage());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/turnNextPage")
    public String turnNextPage() {
        return String.format(TAG_FORMAT, controller.turnNextPage());
    }
}

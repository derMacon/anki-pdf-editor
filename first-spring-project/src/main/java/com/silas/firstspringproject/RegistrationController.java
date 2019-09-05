package com.silas.firstspringproject;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RegistrationController {

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = APPLICATION_JSON_VALUE)
    public String register(@RequestParam(required=false,name="name") User user) {
        System.out.println("user: ");
        System.out.println(user);
        return "funzt";
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());

    }
}

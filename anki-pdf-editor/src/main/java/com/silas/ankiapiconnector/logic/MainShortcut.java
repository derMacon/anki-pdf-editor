package com.silas.ankiapiconnector.logic;

import com.silas.ankiapiconnector.ankiRequest.PostConnector;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainShortcut {

    public static void main(String[] args) throws IOException {

//        PostConnector c = new PostConnector(8080, "addCard");
//        Card card = new Card("asdf", "wer", "asdf", new String[] {"tags", "no"});
//        System.out.println(card.toUrlParam());
//
//        c.urlRequest(card.toUrlParam());

        System.out.println(Pattern.compile("(w|wq)").matcher("wq").find());
        System.out.println("q".matches("(w|wq)"));

    }

}

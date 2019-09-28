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

//        System.out.println(Pattern.compile("(w|wq)").matcher("wq").find());
//        System.out.println("q".matches("(w|wq)"));

        System.out.println("************************************************".length());

    }

    private static String formatStr(String input, int lineLen, char delimiter) {
        int inputLen = input.length() + 2;
        assert inputLen <= lineLen;
        String output = "";

        int offset = (lineLen - inputLen) / 2;
        for (int i = 0; i < offset; i++) {
            output += delimiter;
        }
        output += '\'' + input + '\'';
        for (int i = 0; i < offset; i++) {
            output += delimiter;
        }

        if ((lineLen - inputLen) % 2 != 0) {
            output += delimiter;
        }

        return output;
    }

}

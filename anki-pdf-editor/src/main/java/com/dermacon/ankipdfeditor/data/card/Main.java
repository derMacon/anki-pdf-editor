package com.dermacon.ankipdfeditor.data.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String in = "tags:\nasdf\n\n-------------";

        Pattern pattern = Pattern.compile("tags:(.*)\n-", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(in);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}

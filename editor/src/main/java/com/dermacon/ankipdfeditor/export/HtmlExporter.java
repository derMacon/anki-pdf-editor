package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;

import java.io.File;
import java.util.List;

public class HtmlExporter extends Exporter {

    private static final String FULL_HTML_TEMPLATE =
            "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "  <head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <title>title</title>\n"
                    + "    <link rel=\"stylesheet\" href=\"style.css\">\n"
                    + "    <script src=\"script.js\"></script>\n"
                    + "  </head>\n"
                    + "  <body>\n"
                    + "    %s"
                    + "  </body>\n"
                    + "</html>";

    private static final String CARD_TEMPLATE =
            "<div class=card>"
                    + "<div class=front>\n"
                    + "  %s\n"
                    + "</div>\n"
                    + "<div class=back>\n"
                    + "  %s\n"
                    + "</div>\n"
                    + "<div class=tags>\n"
                    + "  %s\n"
                    + "</div>\n"
                    + "</div>\n";

    private static final String TAG_TEMPLATE = "<ul>%s<ul>";
    private static final String LST_BULLET_POINT = "<li>%s</li>";

    public HtmlExporter(File deckDir) {
        super(deckDir);
    }

    @Override
    String parseFormat(List<Card> stack) {
        StringBuilder output = new StringBuilder();
        for(Card curr : stack) {
            output.append(String.format(CARD_TEMPLATE,
                    curr.getFrontSide(),
                    curr.getBackSide(),
                    createTagLst(curr.getTags())));
        }
        return output.toString();
    }

    private static String createTagLst(String[] tags) {
        StringBuilder output = new StringBuilder();
        for(String tag : tags) {
            output.append(String.format(LST_BULLET_POINT, tag));
        }
        return String.format(TAG_TEMPLATE, output.toString());
    }

}

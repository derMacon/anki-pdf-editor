package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;

import java.util.List;

public class HtmlExporter extends Exporter {

    private static final String CSS =
            "/* raw design: https://dev.to/ananyaneogi/create-a-dark-light-mode-switch-with-css-variables-34l8 */\n" +
                    "\n" +
                    "@import url(https://fonts.googleapis.com/css?family=Lato:400,400italic,700|Sansita+One);\n" +
                    "\n" +
                    ":root {\n" +
                    "    --white_100: #ffffff;\n" +
                    "    --white_95:  #f2f2f2;\n" +
                    "    --white_90:  #e6e6e6;\n" +
                    "    --white_85:  #d9d9d9;\n" +
                    "}\n" +
                    "\n" +
                    "body {\n" +
                    "    font-family: \"Lato\", sans-serif;\n" +
                    "    max-width: 90%%;\n" +
                    "    margin: 0 auto;\n" +
                    "    font-size: calc(1rem + 0.25vh);\n" +
                    "    background-color: var(--white_90);\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "    font-family: \"Sansita One\", serif;\n" +
                    "    font-size: 2rem;\n" +
                    "    margin-bottom: 1vh;\n" +
                    "}\n" +
                    "\n" +
                    "img {\n" +
                    "    max-width: 100%%;\n" +
                    "}\n" +
                    "\n" +
                    ".front {\n" +
                    "    text-align: center;\n" +
                    "}\n" +
                    "\n" +
                    ".back {\n" +
                    "    vertical-align: middle;\n" +
                    "    text-align: center;\n" +
                    "}\n" +
                    "\n" +
                    ".back span {\n" +
                    "    display: inline-block;\n" +
                    "    vertical-align: top;\n" +
                    "    text-align: left;\n" +
                    "\n" +
                    "    padding-top:    10px;\n" +
                    "    padding-right:  10px;\n" +
                    "    padding-bottom: 10px;\n" +
                    "    padding-left:   10px;\n" +
                    "}\n" +
                    "\n" +
                    ".card {\n" +
                    "    max-width: 68%%;\n" +
                    "    margin: 0 auto;\n" +
                    "    margin-top: 25px;\n" +
                    "    margin-bottom: 25px;\n" +
                    "}\n" +
                    "\n" +
                    ".card > * {\n" +
                    "    font-size: 1.1rem;\n" +
                    "    line-height: 1.6rem;\n" +
                    "    background-color: var(--white_100);\n" +
                    "\n" +
                    "    border: 5px solid var(--white_95);\n" +
                    "    padding: 5px;\n" +
                    "    margin: 5px;\n" +
                    "    border-radius: 5px;\n" +
                    "\n" +
                    "    -webkit-box-shadow: 0px 0px 6px 0px rgba(0,0,0,0.15);\n" +
                    "    -moz-box-shadow:    0px 0px 6px 0px rgba(0,0,0,0.15);\n" +
                    "    box-shadow:         0px 0px 6px 0px rgba(0,0,0,0.15);\n" +
                    "}\n" +
                    "\n" +
                    ".post-meta {\n" +
                    "    font-size: 1rem;\n" +
                    "    font-style: italic;\n" +
                    "    display: block;\n" +
                    "    margin-bottom: 4vh;\n" +
                    "}\n";

    private static final String FULL_HTML_TEMPLATE =
            "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "  <head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <title>title</title>\n"
                    + "    <style>"
                    + CSS
                    + "    </style>"
                    + "  </head>\n"
                    + "  <body>\n"
                    + "    %s"
                    + "  </body>\n"
                    + "</html>";

    private static final String CARD_TEMPLATE =
            "<div class=card>"
                    + "<div class=\"front\">\n"
                    + "  %s\n"
                    + "</div>\n"
                    + "<div class=\"back\">\n"
                    + "  <span>%s</span>\n"
                    + "</div>\n"
                    + "<div class=\"tags\">\n"
                    + "  %s\n"
                    + "</div>\n"
                    + "</div>\n";

    private static final String TAG_TEMPLATE = "<ul>%s<ul>";
    private static final String LST_BULLET_POINT = "<li>%s</li>";

    public HtmlExporter(ExportInfo exportInfo) {
        super(exportInfo);
    }

    @Override
    String parseFormat(List<Card> stack) {
        StringBuilder temp = new StringBuilder();
        for(Card curr : stack) {
            temp.append(String.format(CARD_TEMPLATE,
                    curr.getFrontSide(),
                    curr.getBackSide(),
                    createTagLst(curr.getTags())));
        }

        String content = updateImages(temp.toString());
        System.out.println(content);
        return String.format(FULL_HTML_TEMPLATE, content);
    }

    private String updateImages(String content) {
        String regex = "<img src=[\"]?(.*?)[\"]?>";
        return content
                .replaceAll(regex, "<path=\"$1\">")
                .replaceAll("<path=\"", "<img src=\"" + exportInfo.getMediaPath());
    }

    private static String createTagLst(String[] tags) {
        StringBuilder output = new StringBuilder();
        for(String tag : tags) {
            output.append(String.format(LST_BULLET_POINT, tag));
        }
        return String.format(TAG_TEMPLATE, output.toString());
    }

}

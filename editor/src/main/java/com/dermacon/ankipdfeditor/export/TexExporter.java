package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;

import java.util.List;

public class TexExporter extends Exporter {

    private static final String DOC_TEMPLATE = "\\documentclass{article}\n"
            + "\\usepackage{tikz,lipsum,lmodern}\n"
            + "\\usepackage[most]{tcolorbox}\n"
            + "\\usepackage[paperheight=10.75in,paperwidth=7.25in,margin=1in,heightrounded]{geometry}\n"
            + "\\usepackage{graphicx}\n"
            + "\\usepackage{blindtext}\n"
            + "\\usepackage{ragged2e}\n"
            + "\n"
            + "\\graphicspath{ {./img/} }\n"
            + "\n"
            + "\\begin{document}\n"
            + "  %s\n"
            + "\\end{document}\n"
            + "\n";

    private static final String CARD_TEMPLATE = "\\begin{tcolorbox}[colback=white!10!white,colframe=lightgray!75!black,\n"
            + "  savelowerto=\\jobname_ex.tex]\n"
            + "\n"
            + "  \\begin{center}\n"
            + "    %s\n"
            + "  \\end{center}\n"
            + "\n"
            + "  \\tcblower\n"
            + "\n"
            + "  \\justifying\n"
            + "  %s\n"
            + "\n"
            + "\\end{tcolorbox}\n";

    private static final String IMG_TEMPLATE = "\\includegraphics[width=\\linewidth]{%s}\n";

    public TexExporter(ExportInfo exportInfo) {
        super(exportInfo);
    }

    @Override
    String parseFormat(List<Card> stack) {
        StringBuilder temp = new StringBuilder();
        for(Card curr : stack) {
            temp.append(String.format(CARD_TEMPLATE,
                    escapeTexChars(curr.getFrontSide()),
                    escapeTexChars(curr.getBackSide()))
            );
        }

        String content = translateHtml(temp.toString());
        System.out.println(content);
        return String.format(DOC_TEMPLATE, content);
    }

    private String translateHtml(String content) {
        return translateTags(updateImages(content));
    }

    private String translateTags(String content) {
        return content.replaceAll("<div>", "")
                .replaceAll("</div>", "\n");
    }

    // todo check if really necessary
    private String escapeTexChars(String content) {
        return content
                .replaceAll("&", "\\\\&")
                .replaceAll("%", "\\\\%")
                .replaceAll("\\$", "\\\\\\$")
                .replaceAll("#", "\\\\#")
//                .replaceAll("_", "\\\\_")
                .replaceAll("\\{", "\\\\\\{")
                .replaceAll("}", "\\\\}")
                .replaceAll("~", "\\\\~")
                .replaceAll("\\^", "\\\\\\^")
                .replaceAll("\\\\", "\\\\\\\\");
    }

    private String updateImages(String content) {
        String regex = "<img src=[\"]?(.*?)[\"]?>";
        return content
                .replaceAll(regex, "{{path=$1}}")
                .replaceAll("\\{\\{path=", "  \\\\includegraphics[width=\\\\linewidth]{\\\\detokenize{")
                .replaceAll(".png", "")
                .replaceAll(".jpg", "");
    }

}

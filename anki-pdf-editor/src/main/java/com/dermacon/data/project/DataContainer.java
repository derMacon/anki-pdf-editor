package com.dermacon.data.project;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class DataContainer {

    private static final String VIM_USAGE =
            "************************************************\n"
                    + "*         Anki-Editor - version 1.0            *\n"
                    + "*              In normal mode:                 *\n"
                    + "*  - type ,c to add a template for a new card  *\n"
                    + "*  - type ,p to paste the current page number  *\n"
                    + "************************************************\n\n";

    // length of the line used in the description (see VIM_USAGE)
    private static final int LINE_LENGTH = 48; // todo make from VIM_USAGE dependent

    private static final String LAST_DOCS_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/";
    private static final File PROJ_HISTORY = new File(LAST_DOCS_DIR + ".projHistory");
    private static final String DECK_DIR = LAST_DOCS_DIR + "decks/%s.anki";
    private static final String PDF_DIR = LAST_DOCS_DIR + "pdf/%s";

    private ProjectInfo projectInfo;

    public DataContainer(String deck, String pdfName) throws IOException {
        String pdf = String.format(PDF_DIR, pdfName);
        int numberOfPages = PDDocument.load(new File(pdf)).getNumberOfPages();

        projectInfo = new ProjectInfo(
                String.format(DECK_DIR, deck),
                pdf,
                new PageData(1, numberOfPages) // todo builder pattern
        );

        createDeckFile();
        startWorker();
    }

    public DataContainer() throws IOException {
        String deck = null;
        String pdf = null;
        Integer page = null;
        int n_lines = 3;
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(PROJ_HISTORY);

        // parse .projectHistory file
        while (counter < n_lines) {
            String line = object.readLine();
            if (line.startsWith("deck")) {
                deck = String.format(DECK_DIR, line.split(":")[1]);
            } else if (line.startsWith("pdf")) {
                pdf = String.format(PDF_DIR, line.split(":")[1]);
            } else if (line.startsWith("page")) {
                page = Integer.parseInt(line.split(":")[1]);
            } else {
                throw new IOException("input line does not match pattern"); // todo pattern to javadoc
            }
            counter++;
        }

        int numberOfPages = PDDocument.load(new File(pdf)).getNumberOfPages();
        this.projectInfo = new ProjectInfo(
                deck,
                pdf,
                new PageData(page, numberOfPages)
        ); // todo builder pattern

        createDeckFile();
        startWorker();
    }

    private void createDeckFile() {
        String deckFilePath = projectInfo.getDeck();
        String deckDescription = formatDeckdescr(deckFilePath, LINE_LENGTH); // todo

        File file = new File(deckFilePath);
        if (!file.exists() && !file.isDirectory()) {
            try {
                FileUtils.writeStringToFile(file, VIM_USAGE + deckDescription);
                System.out.println("file wrote");
                System.out.println(file);
            } catch (IOException e) {
                System.out.println("Cannot write file " + file + "\n" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists - no need to create a new one");
        }
    }

    private static String formatDeckdescr(String input, int lineLen) {
        char delimiter = '-';
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

        return output + "\n\n";
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public int turnNextPage() {
        PageData pageData = projectInfo.getPages();
        int out = pageData.getCurrPage();
        if (out < pageData.getPageCnt()) {
            out++;
        }
        return out;
    }

    public int turnPrevPage() {
        PageData pageData = projectInfo.getPages();
        int out = pageData.getCurrPage();
        if (out > 0) {
            out--;
        }
        return out;
    }


    public String getCurrPageImage() {
        return "https://upload.wikimedia.org/wikipedia/commons/d/d9/Test.png"; // todo
    }

    private void startWorker() {

    }


}

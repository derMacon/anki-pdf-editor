package com.dermacon.data.project;

import com.dermacon.data.worker.multithreading.Manager;
import com.dermacon.data.worker.multithreading.Renderer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ProjectController {

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
    private Renderer renderer;

    public ProjectController() throws IOException {
        parseProjHistory();
        createDeckFile();
        startWorker();
    }

    /**
     * parse .projectHistory file
     * @throws IOException
     */
    private void parseProjHistory() throws IOException {
        ReversedLinesFileReader object = new ReversedLinesFileReader(PROJ_HISTORY);
        ProjectInfo.InfoBuilder builder = new ProjectInfo.InfoBuilder();
        int counter = 0, n_lines = 3;
        String line;

        while (counter < n_lines) {
            line = object.readLine();
            if (line.startsWith("deck")) {
                builder.deckName(line.split(":")[1]);
            } else if (line.startsWith("pdf")) {
                builder.pdfName(line.split(":")[1]);
            } else if (line.startsWith("page")) {
                builder.currPage(line.split(":")[1]);
            } else {
                throw new IOException("input line does not match pattern: " + line); // todo pattern to javadoc
            }
            counter++;
        }

        this.projectInfo = builder.build();
    }

    private void createDeckFile() {
        String deckFilePath = projectInfo.getDeckPath();
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

    private void startWorker() {
        renderer = new Manager(projectInfo);
        renderer.renderPageSurrounding(projectInfo.getCurrPage());
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public int turnNextPage() {
        int out = projectInfo.getCurrPage();
        if (out < projectInfo.getPdfDoc().getNumberOfPages()) {
            renderer.renderPageSurrounding(out++);
        }
        return out;
    }

    public int turnPrevPage() {
        int out = projectInfo.getCurrPage();
        if (out > 0) {
            renderer.renderPageSurrounding(out--);
        }
        return out;
    }

    public String getCurrPageImage() {
        return "https://upload.wikimedia.org/wikipedia/commons/d/d9/Test.png"; // todo
    }
}

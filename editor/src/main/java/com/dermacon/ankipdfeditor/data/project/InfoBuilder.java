package com.dermacon.ankipdfeditor.data.project;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Generates a project info component.
 */
public class InfoBuilder {

    /**
     * Text that will be written to the beginning of any new .anki file.
     */
    private static final String VIM_USAGE =
            "************************************************\n"
                    + "*       Anki-Vim-Editor - version 1.0          *\n"
                    + "*              In normal mode:                 *\n"
                    + "*  - type ,c to add a template for a new card  *\n"
                    + "*  - type p to paste the current page number   *\n"
                    + "*  - type z to turn to the next page           *\n"
                    + "*  - type Z to turn to the previous page       *\n"
                    + "*                                              *\n"
                    + "*  hint:                                       *\n"
                    + "*  - If page is not available with simply      *\n"
                    + "*    typing p try ,p                           *\n"
                    + "*  - To tab between fields use tab or shift    *\n"
                    + "*    plus tab                                  *\n"
                    + "************************************************\n\n";

    /**
     * Directory where all property related files / directories are located
     */
    private static final String LAST_DOCS_DIR = System.getProperty("user.dir") + "/lastDocs/";
    /**
     * Directory where any rendered images will be located.
     */
    private static final String IMG_TEMP_DIR = LAST_DOCS_DIR + "img_temp/";

    /**
     * Directory where all .anki files are located.
     */
    private static final String DECK_DIR = LAST_DOCS_DIR + "decks/";
    private static final String DECK_FILE = DECK_DIR + "%s.anki";

    /**
     * Directory where all .pdf files are located.
     */
    private static final String PDF_DIR = LAST_DOCS_DIR + "pdf/";
    private static final String PDF_FILE = PDF_DIR + "%s.pdf";

    /**
     * Directory where all config files are located
     * example: session .vimrc
     */
    private static final String CONFIG_DIR = LAST_DOCS_DIR + "config/";

    /**
     * Directory where all exported files will be saved to
     */
    private static final String EXPORT_DIR = LAST_DOCS_DIR + "export/";

    private static final File SESSION_VIMRC = new File(CONFIG_DIR + ".vimrc");
    private static final File PROJ_HISTORY = new File(LAST_DOCS_DIR + ".projHistory");
    private static final String DEFAULT_DECK = String.format(DECK_FILE, "TestDeck");
    private static final String DEFAULT_PDF = String.format(PDF_FILE, "manual");

    // these resource path descriptions are only valid with context of the classpath
    private static final String MANUAL_RES_PATH = "/com/dermacon/ankipdfeditor/manual.pdf";
    private static final String VIMRC_RES_PATH = "/com/dermacon/ankipdfeditor/.vimrc";

    // media directory
    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String ANKI_IMG_PAGES = HOME_DIR + "/.local/share/Anki2/User 1/collection.media/";


    /**
     * .anki file to which the user is actively writing to.
     */
    private File deck = new File(DEFAULT_DECK);
    private File pdf = new File(DEFAULT_PDF);
    private int currPage = 1;
    private String exportDir = EXPORT_DIR;

    /**
     * Initiates a new InfoBuilder instance with a given project
     * info component. Especially useful when the properties need
     * to be modified and the original instance should not be effected.
     * @param oldInstance project info instance from which the builder
     *                    should be instanciated.
     * @return Builder instance containing the information of the given
     * projectinfo instance.
     */
    public InfoBuilder copy(ProjectInfo oldInstance) {
        this.deck = oldInstance.getDeckFile();
        this.pdf = oldInstance.getPdf();
        this.currPage = oldInstance.getCurrPage();

        return this;
    }

    /**
     * - If the project structure (directories starting with ./lastDocs/.../) are not existent
     * this function instanciates them.
     * - Returns a new ProjectInfo instance containing either the default values or their
     * overwritten conterparts.
     * @return new ProjectInfo instance
     * @throws IOException if the project structure cannot be initialized.
     */
    public ProjectInfo build() throws IOException {
        initProjectStructure();
        return new ProjectInfo(deck, pdf, PROJ_HISTORY, SESSION_VIMRC, IMG_TEMP_DIR, exportDir, ANKI_IMG_PAGES, currPage);
    }

    /**
     * Initializes the project structure using the /lastdocs/ directory.
     * Copies the meta data to these newly created directories.
     * @throws IOException exception that will be thrown if anki is not responsive.
     */
    private void initProjectStructure() throws IOException {
        saveMkDir(LAST_DOCS_DIR);
        saveMkDir(DECK_DIR);
        saveMkDir(PDF_DIR);
        saveMkDir(IMG_TEMP_DIR);
        saveMkDir(CONFIG_DIR);
        saveMkDir(EXPORT_DIR);

        copyResource(MANUAL_RES_PATH, DEFAULT_PDF);
        copyResource(VIMRC_RES_PATH, CONFIG_DIR + ".vimrc");

        // todo maybe: setDeck(deck.getName());
        if (!deck.exists()) {
            createDeckFile(deck);
        }
    }

    /**
     * Creates the directory at the specified directory if it's not already created
     * @param path path to the directory that will be created.
     */
    private void saveMkDir(String path) {
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
    }

    /**
     * read resource: https://stackoverflow.com/questions/20389255/reading-a-resource-file-from-within-jar
     * copy inputstream: https://www.baeldung.com/convert-input-stream-to-a-file
     * @param resourcePath
     * @param targetPath
     * @throws IOException
     */
    private void copyResource(String resourcePath, String targetPath) throws IOException {
        InputStream in = getClass().getResourceAsStream(resourcePath);
        FileUtils.copyInputStreamToFile(in, new File(targetPath));
    }

    // todo maybe delete
    private void copyResource(File srcFile, File targetFile) throws IOException {
        if (((!targetFile.exists() || !targetFile.isDirectory()))
                && !targetFile.equals(srcFile)) {
            FileUtils.copyFile(srcFile, targetFile);
        }
    }

    /**
     * Sets pdf file instance in the project info component and copies the
     * given file to the last docs dir, but only if wasn't already copied before.
     * @param pdf
     * @return
     * @throws IOException
     */
    public InfoBuilder setPdf(File pdf) throws IOException {
        return setPdf(pdf.getName());
    }

    /**
     * Sets the file in the projectInfo component.
     * @param pdfName name of the file
     * @return current builder instance
     */
    public InfoBuilder setPdf(String pdfName) {
        File targetFile = new File(PDF_DIR + pdfName);
//        copyResource(pdf, targetFile);
        this.pdf = targetFile;
        return this;
    }

    /**
     * Setter for the deck
     * @param deckName deckname
     * @return current builder instance
     * @throws IOException thrown when the directory cannot be created.
     */
    public InfoBuilder setDeck(String deckName) throws IOException {
        deck = new File(DECK_DIR + deckName);
        if (!deck.exists() && !deck.isDirectory()) {
            createDeckFile(deck);
        }
        return this;
    }

    /**
     * Setter for the current page
     * @param currPage current page of the document
     */
    public void setCurrPage(String currPage) {
        this.currPage = Integer.parseInt(currPage.trim());
    }

    /**
     * Setter for the export path
     * @param path path to export dir
     */
    public void setExportDir(String path) {
        this.exportDir = path;
    }

    /**
     * parse /lastDocs/.projectHistory file
     *
     * @throws IOException thrown if the project history cannot be read.
     */
    public InfoBuilder parseHistoryFile() throws IOException {
        if (PROJ_HISTORY.exists()) {
            ReversedLinesFileReader object = new ReversedLinesFileReader(PROJ_HISTORY);
            int counter = 0, n_lines = 4;
            String line;

            while (counter < n_lines) {
                line = object.readLine();
                if (line != null) {
                    if (line.startsWith("deck")) {
                        setDeck(line.split(":")[1].trim());
                    } else if (line.startsWith("pdf")) {
                        setPdf((line.split(":")[1].trim()));
                    } else if (line.startsWith("page")) {
                        setCurrPage(line.split(":")[1].trim());
                    } else if (line.startsWith("exp")) {
                        setExportDir(line.split(":")[1].trim());
                    } else if (!line.isEmpty()) {
                        throw new IOException("input line does not match pattern: " + line); // todo pattern to javadoc
                    }
                }
                counter++;
            }
        }

        return this;
    }

    /**
     * Creates a given deckfile in the project structure and initializes with the
     * the given VIM_USAGE.
     * @param deckFile deckfile file that will be initialized.
     * @throws IOException thrown if the string content cannot be written to the specified file.
     */
    private void createDeckFile(File deckFile) throws IOException {
        int lineLength = VIM_USAGE.split("\n")[0].length();
        String deckDescription = formatDeckdescr(deckFile.getName(),lineLength);
        FileUtils.writeStringToFile(deckFile, VIM_USAGE + deckDescription);
    }

    /**
     * Puts a given string input in the middle of a new string and pre- / appends a delimiter
     * to the it.
     * @param input input that will be centered / formatted.
     * @param lineLen total length of the output string.
     * @return formatted string.
     */
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

}

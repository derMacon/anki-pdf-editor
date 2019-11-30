package com.dermacon.ankipdfeditor.data.worker.parser;

import com.dermacon.ankipdfeditor.data.card.IncompleteSyntaxException;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlCardParser {

    private static final String TEMP_PDF_PAGES = System.getProperty("user.dir") + "/src/main/resources/META-INF/resources/tempPages/pdf/";
    private static final String TEMP_IMG_PAGES = System.getProperty("user.dir") + "/src/main/resources/META-INF/resources/tempPages/png/";
    // todo this should come from the projectinfo component
    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String ANKI_IMG_PAGES = HOME_DIR + "/.local/share/Anki2/User 1/collection.media/";
    private static final String HTML_LINE_FORMAT = "<div>%s</div>";

    private static final int DEFAULT_WIDTH = 930;
    private static final int DEFAULT_HEIGHT = 650;

    /**
     * Default output resolution of the images (in dots per inch)
     */
    public static int DEFAULT_DPI = 150;

    private PDDocument doc;
    private String pdfName;
    private final String FILE_TEMPLATE = TEMP_IMG_PAGES + pdfName + "_%s.png"; // todo ?

    public HtmlCardParser(String path) throws IOException {
        this(new File(path));
    }

    public HtmlCardParser(File file) throws IOException {
        assert file != null && file.isFile();
        this.doc = PDDocument.load(file);
        this.pdfName = FilenameUtils.removeExtension(file.getName());
    }

    public String getPdfName() {
        return pdfName;
    }

    /**
     * Parses a given card side to the html components.
     * - Wraps whole lines in <div> - Tags
     * - Wraps the costum image tags into html image tags
     * - maps the escaped german umlaute to the appropriate representation
     *
     * @param side front- / back-side from an anki card
     * @return html String
     * @throws IOException
     */
    public String parseHtml(String side) throws IOException {
        StringBuilder out = new StringBuilder();
        for (String line : side.split("\n")) {
            out.append(String.format(HTML_LINE_FORMAT, line));
        }
        return mapGermanUmlaute(parseImg(out.toString()));
    }

    private String parseImg(String side) throws IOException {
        int[] nums = extractAllNumbers(side);
        for (int curr : nums) {
            renderImageInTemp(curr);
        }
        return generateImgHtmlTag(side);
    }

    private String generateImgHtmlTag(String side) {
        return side.replaceAll("<(\\d*)>", "<img src=" + pdfName + "_$1.png>");
    }

    /**
     * Maps
     * "a to ä / "A to Ä
     * "u to ü / "U to Ü
     * "o to Ö / "O to Ö
     *
     * @param side
     * @return
     */
    private String mapGermanUmlaute(String side) {
        return side.replaceAll("\"a", "ä")
                .replaceAll("\"A", "Ä")
                .replaceAll("\"u", "ü")
                .replaceAll("\"U", "Ü")
                .replaceAll("\"o", "ö")
                .replaceAll("\"O", "Ö");
    }

    /**
     * Renders an image of the given page given that the page is actually existent in the underlying pdf document
     *
     * @param pageNum page num which the user selected
     * @return an cliboard image which can be saved in the systems clipboard
     * @throws IOException Exception that will be thrown if the selected pdf document cannot be read
     */
    private void renderImageInTemp(Integer pageNum) throws IOException {
        PDFRenderer pdfRenderer = new PDFRenderer(this.doc);
        BufferedImage bim = pdfRenderer.renderImageWithDPI(pageNum - 1, DEFAULT_DPI, ImageType.RGB);
        String path = ANKI_IMG_PAGES + pdfName + "_" + pageNum + ".png";
        File currPageImg = new File(path);
        if (!currPageImg.exists()) {
            ImageIOUtil.writeImage(bim, currPageImg.getPath(), DEFAULT_DPI);
        }
        ImageResizer.resizeImage(currPageImg.getPath(), currPageImg.getPath(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static int[] extractAllNumbers(String input) {
        return Arrays.stream(input.replaceAll("[^0-9]+", " ")
                .split(" "))
                .filter(e -> e.length() > 0)
                .mapToInt(Integer::parseInt)
                .toArray();

//        String regex = ".*(\\{([0-9])*?\\}.*)*.*";
//        String regex = "(.*?\\{(.*?)\\}.*?)*?";
//        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
//        Matcher matcher = pattern.matcher(input);
//
//        if (!matcher.find()) {
////            throw new IncompleteSyntaxException("one of the fields is empty:\n" + cardBlock);
//        }
//        String temp1 = matcher.group(0);
//        String temp2 = matcher.group(1);
//        String temp3 = matcher.group(2);
////        String temp4 = matcher.group(3);
//
//        System.out.println("temp1: " + temp1);
//        System.out.println("temp2: " + temp2);
//        System.out.println("temp3: " + temp3);
////        System.out.println("temp4: " + temp4);
//        return null;
    }

}

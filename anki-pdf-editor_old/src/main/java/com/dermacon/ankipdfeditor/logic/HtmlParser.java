package com.dermacon.ankipdfeditor.logic;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class HtmlParser {

    private static final String TEMP_PDF_PAGES = System.getProperty("user.dir") + "/src/main/resources/META-INF/resources/tempPages/pdf/";
    private static final String TEMP_IMG_PAGES = System.getProperty("user.dir") + "/src/main/resources/META-INF/resources/tempPages/png/";
    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String ANKI_IMG_PAGES = HOME_DIR + "/.local/share/Anki2/User 1/collection.media/";

    private static final int DEFAULT_WIDTH = 930;
    private static final int DEFAULT_HEIGHT = 650;

    /**
     * Default output resolution of the images (in dots per inch)
     */
    public static int DEFAULT_DPI = 150;

    private PDDocument doc;
    private String pdfName;
    private final String FILE_TEMPLATE = TEMP_IMG_PAGES + pdfName + "_%s.png";

    public HtmlParser(String path) throws IOException {
        this(new File(path));
    }

    public HtmlParser(File file) throws IOException {
        assert file != null && file.isFile();
        this.doc = PDDocument.load(file);
        this.pdfName = FilenameUtils.removeExtension(file.getName());
    }

    public String getPdfName() {
        return pdfName;
    }

    public Card parseImgTag(final Card input) throws IOException {
        return new Card(input.getDeckName(),
                parseImg(input.getFrontSide()),
                parseImg(input.getBackSide()),
                input.getTags()
        );
    }

    public String parseImg(String side) throws IOException {
        int[] nums = extractAllNumbers(side);
//        Arrays.stream(nums).forEach(this::renderImageInTemp);
        for (int curr : nums) {
            renderImageInTemp(curr);
        }
        return side.replaceAll("<(\\d*)>", "<img src=" + pdfName + "_$1.png>");
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
        ImageIOUtil.writeImage(bim, currPageImg.getPath(), DEFAULT_DPI);
        ImageResizer.resizeImage(currPageImg.getPath(), currPageImg.getPath(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public int[] extractAllNumbers(String input) {
        return Arrays.stream(input.replaceAll("[^0-9]+", " ")
                .split(" "))
                .filter(e -> e.length() > 0)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}

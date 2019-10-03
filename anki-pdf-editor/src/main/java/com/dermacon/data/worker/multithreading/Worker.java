package com.dermacon.data.worker.multithreading;

import com.dermacon.data.project.ProjectInfo;
import com.dermacon.data.worker.parser.ImageResizer;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Worker implements Runnable {

    private static final int DEFAULT_WIDTH = 930;
    private static final int DEFAULT_HEIGHT = 650;

    /**
     * Default output resolution of the images (in dots per inch)
     */
    private static int DEFAULT_DPI = 150;

    private static String IMG_TEMP_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/img_temp/";

    private final Assignments assignments;
    private final ProjectInfo projectInfo;

    public Worker(Assignments assignments, ProjectInfo projectInfo) {
        this.assignments = assignments;
        this.projectInfo = projectInfo;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                render(assignments.getAssignment());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void render(Integer pageNum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " processes page " + pageNum);
        // todo
    }

    /**
     * Renders an image of the given page given that the page is actually existent in the underlying pdf document
     *
     * @param pageNum page num which the user selected
     * @return an cliboard image which can be saved in the systems clipboard
     * @throws IOException Exception that will be thrown if the selected pdf document cannot be read
     */
    private void renderImageInTemp(Integer pageNum) throws IOException {
        PDDocument pdf = this.projectInfo.getPdfDoc();
        PDFRenderer pdfRenderer = new PDFRenderer(pdf);
        BufferedImage bim = pdfRenderer.renderImageWithDPI(pageNum - 1, DEFAULT_DPI, ImageType.RGB);
        String path = projectInfo.getPdfName() + "_" + pageNum + ".png";
        File currPageImg = new File(path);
        ImageIOUtil.writeImage(bim, currPageImg.getPath(), DEFAULT_DPI);
        ImageResizer.resizeImage(currPageImg.getPath(), currPageImg.getPath(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}

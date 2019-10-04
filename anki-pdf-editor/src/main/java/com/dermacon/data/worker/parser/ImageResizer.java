package com.dermacon.data.worker.parser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * https://deano.me/2012/02/java-resize-an-image-keeping-the-proportions-correct/
 *
 * @author dean.williams
 */
public class ImageResizer {

    /**
     * Resize an image
     * By Dean Williams - http://dean.resplace.net
     *
     * @param sourceImg The source of the image to resize.
     * @param destImg   The destination of the resized image.
     * @param Width     The maximum width you want the new image to be, use 0 for source width.
     * @param Height    The maximum height you want the new image to be, use 0 for source height.
     * @return true if successful and false if unsuccessful.
     */
    public static Boolean resizeImage(String sourceImg, String destImg, Integer Width, Integer Height) {
        BufferedImage origImage;
        try {

            origImage = ImageIO.read(new File(sourceImg));
            int type = origImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : origImage.getType();

            //*Special* if the width or height is 0 use image src dimensions
            if (Width == 0) {
                Width = origImage.getWidth();
            }
            if (Height == 0) {
                Height = origImage.getHeight();
            }

            int fHeight = Height;
            int fWidth = Width;

            //Work out the resized width/height
            if (origImage.getHeight() > Height || origImage.getWidth() > Width) {
                fHeight = Height;
                int wid = Width;
                float sum = (float) origImage.getWidth() / (float) origImage.getHeight();
                fWidth = Math.round(fHeight * sum);

                if (fWidth > wid) {
                    //rezise again for the width this time
                    fHeight = Math.round(wid / sum);
                    fWidth = wid;
                }
            }

            BufferedImage resizedImage = new BufferedImage(fWidth, fHeight, type);
            Graphics2D g = resizedImage.createGraphics();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawImage(origImage, 0, 0, fWidth, fHeight, null);
            g.dispose();

            ImageIO.write(resizedImage, "png", new File(destImg));

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Cannot read file: " + sourceImg + " - " + destImg);
        }

        return true;
    }
}


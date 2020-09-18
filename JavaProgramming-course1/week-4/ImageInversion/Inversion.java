import edu.duke.*;
import java.io.*;

public class Inversion {
    public static ImageResource makeInv(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel outPixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(outPixel.getX(), outPixel.getY());

            outPixel.setRed(255 - inPixel.getRed());
            outPixel.setGreen(255 - inPixel.getGreen());
            outPixel.setBlue(255 - inPixel.getBlue());
        }

        return outImage;
    }

    public static void main(String[] args) {
        DirectoryResource dr = new DirectoryResource();
        new File("inverted-images").mkdir();

        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInv(inImage);
            
            outImage.setFileName("inverted-images/inverted-" + inImage.getFileName());
            outImage.save();
        }
    }
}

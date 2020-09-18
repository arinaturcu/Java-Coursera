import edu.duke.*;
import java.io.*;

class BatchGrayscale {
    public static ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel outPixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(outPixel.getX(), outPixel.getY());

            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;

            outPixel.setRed(average);
            outPixel.setGreen(average);
            outPixel.setBlue(average);
        }

        return outImage;
    }

    public static void main(String[] args) {
        DirectoryResource dr = new DirectoryResource();
        new File("gray-images").mkdir();

        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            
            outImage.setFileName("gray-images/gray-" + f.getName());
            outImage.save();
        }
    }
}
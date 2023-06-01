package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * tests for imageWriter class checking simple images.
 */
class ImageWriterTest {

    /**
     * Tests the functionality of writing pixels to an image file.
     * It creates an `ImageWriter` object and iterates through each pixel,
     * writing a red color if the pixel coordinates are divisible by 50,
     * and yellow otherwise. Finally, it saves the image file.
     */
    @Test
    void writeToImage() {
        final int width = 801;
        final int height = 501;
        final int step = 50;
        final Color red = new Color(java.awt.Color.RED);
        final Color yellow = new Color(java.awt.Color.YELLOW);

        ImageWriter imageWriter = new ImageWriter("exe5first", width, height);
        // Iterate through each pixel and write colors
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                    imageWriter.writePixel(i, j, j % step == 0 || i % step == 0 ? red : yellow);
        imageWriter.writeToImage();
    }
}
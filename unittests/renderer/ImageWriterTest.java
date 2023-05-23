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
        // Create an ImageWriter object
        ImageWriter imageWriter = new ImageWriter("exe5first", 800, 500);

        // Iterate through each pixel and write colors
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (j % 50 == 0 || i % 50 == 0) {
                    // Write red color to pixels divisible by 50
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.RED));
                } else {
                    // Write yellow color to other pixels
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.YELLOW));
                }
            }
        }

        // Save the image file
        imageWriter.writeToImage();
    }
}
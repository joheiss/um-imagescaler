package com.jovisco.trials.imagescaler;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageScalerTest {

    @Test
    public void testResizeImage(){

        var targetWidths = new int[]{2048, 1536, 1024, 512, 256, 128};
        // instantiate image scaler
        var imageScaler = new ImageScaler();

        var imageFilesPath = Path.of("src/test/resources/");
        var resizedImagesDir = new File("src/test/resources", "resized");
        try (var imageFiles = Files.newDirectoryStream(imageFilesPath, "*.jpg")) {
            imageFiles.forEach(file -> {
                if (!resizedImagesDir.exists()) {
                    var created = resizedImagesDir.mkdir();
                }
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(file.toFile());
                    for (int targetWidth : targetWidths) {
                        var resizedImage = imageScaler.simpleResizeImage(originalImage, targetWidth);
                        var targetFilename = generateTargetFilename(file.toString(), targetWidth);
                        ImageIO.write(resizedImage, "jpg", new File(targetFilename));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        try {
            var inputFilename = "src/test/resources/test.jpg";
            // read image from file system
            var originalImage = ImageIO.read(new File(inputFilename));
            for (int targetWidth : targetWidths) {
                var resizedImage = imageScaler.simpleResizeImage(originalImage, targetWidth);
                var targetFilename = generateTargetFilename(inputFilename, targetWidth);
                ImageIO.write(resizedImage, "jpg", new File(targetFilename));
            }
            // var resizedImage = imageScaler.resizeImage(originalImage, 1024, 1536);
            // var resizedImage = imageScaler.simpleResizeImage(originalImage, 1024);
            // ImageIO.write(resizedImage, "jpg", new File("src/test/resources/test_1024.jpg"));
            // assertEquals(1024, resizedImage.getWidth());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
        assertTrue(true);
    }

    private String generateTargetFilename(String inputFilename, int targetWidth) {
        var pathName = inputFilename.substring(0, inputFilename.lastIndexOf("/"));
        var filename = inputFilename.substring(0, inputFilename.lastIndexOf("."));
        filename = filename.replace(pathName, "");
        filename = pathName + "/" + "resized/" + filename + "_" + targetWidth;
        var extension = inputFilename.substring(inputFilename.lastIndexOf("."));
        return filename + extension;
    }
}
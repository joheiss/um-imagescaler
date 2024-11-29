package com.jovisco.trials.imagescaler;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

public class ImageScaler {

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(
                originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }

    public BufferedImage simpleResizeImage(BufferedImage originalImage, int targetWidth) {
        return Scalr.resize(originalImage, targetWidth);
    }
}

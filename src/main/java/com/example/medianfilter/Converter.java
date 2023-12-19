package com.example.medianfilter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Converter {
    public static Color[][] imageToColors(Image image){
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        Color[][] pixels = new Color[width][height];

        PixelReader pixelReader = image.getPixelReader();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = pixelReader.getColor(x, y);
                pixels[x][y] = color;
            }
        }
        return pixels;
    }

    public static Image colorsToImage(Color[][] pixelArray) {
        int width = pixelArray[0].length;
        int height = pixelArray.length;

        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelArray[y][x];
                pixelWriter.setColor(y, x, color);
            }
        }
        return image;
    }
}

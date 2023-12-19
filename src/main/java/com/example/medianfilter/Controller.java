package com.example.medianfilter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.net.URL;

import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView imageNoised;
    @FXML
    private ImageView imageFiltered;
    private Color[][] pixels;

    private static final String FILENAME = "C:\\Users\\pro10\\IdeaProjects\\MediaFilter\\src\\main\\resources\\images\\parrot.jpg";

    public void click(){
        Noise.addNoise(pixels);

        imageNoised.setImage(convertToImage(pixels));

        for (int i = 0; i < 3; i++){
            pixels = MedianFilter.applyHorizontalMedianFilter(pixels);
        }

        imageFiltered.setImage(convertToImage(pixels));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(FILENAME);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        pixels = new Color[width][height];

        PixelReader pixelReader = image.getPixelReader();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = pixelReader.getColor(x, y);
                pixels[x][y] = color;
            }
        }
        imageNoised.setImage(image);
    }
    public Image convertToImage(Color[][] pixelArray) {
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


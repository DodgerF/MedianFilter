package com.example.medianfilter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.nio.IntBuffer;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView imageNoised;
    @FXML
    private ImageView imageFiltered;

    private Color[][] pixels;

    private static final String FILENAME = "C:\\Users\\pro10\\IdeaProjects\\MediaFilter\\src\\main\\resources\\images\\parrot.jpg";

    public void click(){
        imageNoised.setImage(convertToImage(pixels));
        imageFiltered.setImage(new Image(FILENAME));
    }

    private static int getGrayValue(int argb) {
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = argb & 0xFF;
        return (int) (0.2989 * r + 0.5870 * g + 0.1140 * b);
    }

    private static void addNoise(Color[][] imageArray, double rate) {
        int numPixels = (int) (imageArray.length * imageArray[0].length * rate);
        Random random = new Random();

        for (int i = 0; i < numPixels; i++) {
            int x = random.nextInt(imageArray[0].length);
            int y = random.nextInt(imageArray.length);

            double c = random.nextDouble();
            if (c < 0.5) {
                imageArray[y][x] = Color.BLACK; // or any other color of your choice
            } else {
                imageArray[y][x] = Color.WHITE; // or any other color of your choice
            }
        }
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


        double noiseRate = 0.5;
        addNoise(pixels, noiseRate);
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

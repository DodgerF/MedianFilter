package com.example.medianfilter;

import javafx.scene.paint.Color;

import java.util.Random;

public class Noise {
    private static final double RATE = 0.5;
    public static void addNoise(Color[][] imageArray) {
        int numPixels = (int) (imageArray.length * imageArray[0].length * RATE);
        Random random = new Random();

        for (int i = 0; i < numPixels; i++) {
            int x = random.nextInt(imageArray[0].length);
            int y = random.nextInt(imageArray.length);

            double c = random.nextDouble();
            if (c < 0.5) {
                imageArray[y][x] = Color.BLACK;
            } else {
                imageArray[y][x] = Color.WHITE;
            }
        }
    }
}

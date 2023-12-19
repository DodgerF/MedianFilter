package com.example.medianfilter;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class MedianFilter {
    public static Color[][] applyHorizontalMedianFilter(Color[][] input) {
        int height = input.length;
        int width = input[0].length;
        Color[][] output = new Color[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double[] values = new double[5];

                for (int i = 0; i < 5; i++) {
                    int neighborX = x - 2 + i;
                    if (neighborX < 0) {
                        neighborX = 0;
                    } else if (neighborX >= width) {
                        neighborX = width - 1;
                    }

                    values[i] = input[y][neighborX].getBrightness();
                }

                output[y][x] = Color.gray(median(values));
            }
        }

        return output;
    }

    private static double median(double[] values) {
        Arrays.sort(values);
        int middle = values.length / 2;
        if (values.length % 2 == 0) {
            return (values[middle - 1] + values[middle]) / 2.0;
        } else {
            return values[middle];
        }
    }
}

package com.example.medianfilter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.net.URL;

import java.util.ResourceBundle;

import static com.example.medianfilter.Converter.colorsToImage;

public class Controller implements Initializable {

    @FXML
    private ImageView imageNoised;
    @FXML
    private ImageView imageFiltered;
    @FXML
    private ImageView imageInitial;
    private final String FILE_NAME = "C:\\Users\\pro10\\IdeaProjects\\MediaFilter\\src\\main\\resources\\images\\parrot.jpg";
    private final int NUMBER_OF_ITERATIONS = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Color[][] pixels = Converter.imageToColors(new Image(FILE_NAME));
        imageInitial.setImage(colorsToImage(pixels));

        Noise.addNoise(pixels);
        imageNoised.setImage(colorsToImage(pixels));

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++){
            pixels = MedianFilter.applyHorizontalMedianFilter(pixels);
        }
        imageFiltered.setImage(colorsToImage(pixels));
    }
}


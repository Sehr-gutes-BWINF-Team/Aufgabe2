package com.benning.joshua.aufgabe2.v1;

import com.benning.joshua.aufgabe2.v1.arguments.ArgumentConverter;
import com.benning.joshua.aufgabe2.v1.model.ImageGenerator;

import java.io.IOException;
import java.util.List;

public class Verzinkt {

    public static void main(String[] args) {
        List<String> argsList = List.of(args);
        ArgumentConverter argumentConverter = new ArgumentConverter(argsList);
        try {
            new ImageGenerator(argumentConverter.getImageGenData(), argumentConverter.getGrowSpeedGenerator(), argumentConverter.getGenerationData()).generateImage();
        } catch (RuntimeException | IOException e) {
            System.out.println("Image generation failed. \n Stacktrace: \n");
            e.printStackTrace();
        }
    }
}

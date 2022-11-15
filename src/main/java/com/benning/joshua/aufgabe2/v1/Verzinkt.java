package com.benning.joshua.aufgabe2.v1;


import com.benning.joshua.aufgabe2.v1.model.GrowSpeed;
import com.benning.joshua.aufgabe2.v1.model.ImageData;
import com.benning.joshua.aufgabe2.v1.model.Pixel;

import java.awt.*;
import java.io.IOException;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Verzinkt {

    public static void main(String[] args) throws IOException {
        ImageData imageData = new ImageData(100, 100, Color.BLACK);
        Random random = new Random();
        Queue<Pixel> pixelsToExpand = new ConcurrentLinkedQueue<>();
        generateStartPoints(imageData, random, pixelsToExpand, 1);
        int sth = 0;
        while (pixelsToExpand.size() != 0 && sth < 1500) {
            Pixel poll = pixelsToExpand.poll();
            if (poll != null) poll.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.addAll(imageData.expand(poll));
            sth++;
        }
        imageData.render(1);
    }

    private static void generateStartPoints(ImageData imageData, Random random, Queue<Pixel> pixelsToExpand, int count) {
        for (int spawnPoint = 0; spawnPoint < count; spawnPoint++) {
            //Pixel startPxl = imageData.getData()[random.nextInt(imageData.getWidth())][random.nextInt(imageData.getHeight())];
            Pixel startPxl = imageData.getData()[imageData.getWidth() / 2][imageData.getHeight() / 2];
            if (startPxl.getPixelState() != Pixel.PixelState.EMPTY) continue;
            int rgbValue = (int) (random.nextFloat(0.5f, 1.0f) * 200);
            startPxl.setColor(new Color(rgbValue - 20, rgbValue, rgbValue));

            /*float gsN = random.nextFloat(0.4f, 1.0f);
            float gsS = random.nextFloat(0.4f, 1.0f);
            float gsW = random.nextFloat(0.4f, 1.0f);
            float gsE = random.nextFloat(0.4f, 1.0f);*/

            float gsN = 1.0f;
            float gsS = 0.2f;
            float gsW = 0.2f;
            float gsE = 0.2f;

            startPxl.setGrowSpeed(new GrowSpeed(gsN, gsS, gsW, gsE));
            startPxl.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.add(startPxl);
        }
    }
}

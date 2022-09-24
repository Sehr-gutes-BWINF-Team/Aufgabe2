package com.benning.joshua.aufgabe2;

import com.benning.joshua.aufgabe2.gif.SequenceWriter;
import com.benning.joshua.aufgabe2.model.GrowSpeed;
import com.benning.joshua.aufgabe2.model.ImageData;
import com.benning.joshua.aufgabe2.model.Pixel;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Verzinkt {

    public static void main(String[] args) throws IOException {
        ImageData imageData = new ImageData(250, 250, Color.BLACK);
        Random random = new Random();
        Queue<Pixel> pixelsToExpand = new ConcurrentLinkedQueue<>();
        for (int spawnPoint = 0; spawnPoint < 100; spawnPoint++) {
            Pixel startPxl = imageData.getData()[random.nextInt(imageData.getWidth())][random.nextInt(imageData.getHeight())];
            startPxl.setColor(new Color(72, random.nextInt(50, 220), 173));

            float gsN = random.nextFloat(0.7f, 1.0f);
            float gsS = random.nextFloat(0.7f, 1.0f);
            float gsW = random.nextFloat(0.7f, 1.0f);
            float gsE = random.nextFloat(0.7f, 1.0f);

            startPxl.setGrowSpeed(new GrowSpeed(gsN, gsS, gsW, gsE));
            startPxl.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.add(startPxl);
        }
        int sth = 0;
        while (pixelsToExpand.size() != 0) {
            Pixel poll = pixelsToExpand.poll();
            if (poll != null) poll.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.addAll(imageData.expand(poll));
            sth++;
        }
        imageData.render(1);
    }
}

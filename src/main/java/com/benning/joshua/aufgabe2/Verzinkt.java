package com.benning.joshua.aufgabe2;

import com.benning.joshua.aufgabe2.model.ImageData;
import com.benning.joshua.aufgabe2.model.Pixel;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Verzinkt {

    public static void main(String[] args) {
        ImageData imageData = new ImageData(50, 50, Color.BLACK);
        Pixel pixel = imageData.getData()[15][25];
        pixel.setColor(Color.MAGENTA);

        Pixel pixel2 = imageData.getData()[35][25];
        pixel2.setColor(Color.YELLOW);

        Queue<Pixel> pixelsToExpand = new ConcurrentLinkedQueue<>();
        pixelsToExpand.add(pixel);
        pixelsToExpand.add(pixel2);
        int sth = 0;
        while (pixelsToExpand.size() != 0) {
            pixelsToExpand.addAll(imageData.expand(pixelsToExpand.poll()));
            sth++;
            imageData.render(sth);
        }
        imageData.expand(pixel);
    }
}

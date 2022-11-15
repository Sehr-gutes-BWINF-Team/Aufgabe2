package com.benning.joshua.aufgabe2.v2;


import com.benning.joshua.aufgabe2.v2.gif.SequenceWriter;
import com.benning.joshua.aufgabe2.v2.model.Crystal;
import com.benning.joshua.aufgabe2.v2.model.Position;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VerzinktV2 {

    public static void main(String[] args) throws IOException {

        List<Crystal> crystals = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            float gsN = random.nextFloat(0.1f, 2.0f);
            float gsS = random.nextFloat(0.1f, 2.0f);
            float gsW = random.nextFloat(0.1f, 2.0f);
            float gsE = random.nextFloat(0.1f, 2.0f);

            //crystals.add(new Crystal(new Color(20 + random.nextInt(100), 20 + random.nextInt(100), 208),gsN, gsS, gsE, gsW,
                    //new Position(random.nextInt(150), random.nextInt(150))));
        }


        try {


            List<BufferedImage> images = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                BufferedImage image = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                for (Crystal crystal : crystals) {
                    crystal.grow();
                    crystal.draw(image.createGraphics());
                }
                images.add(image);
            }


            BufferedImage first = images.get(0);

            ImageOutputStream output = new FileImageOutputStream(new File("temp/example.gif"));
            ImageIO.write(images.get(images.size() - 1), "PNG", new File("temp/lul.png"));

            SequenceWriter writer = new SequenceWriter(output, first.getType(), 10, true);
            writer.writeToSequence(first);

            for (int i = 1; i < images.size(); i++) {
                writer.writeToSequence(images.get(i));
            }

            writer.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

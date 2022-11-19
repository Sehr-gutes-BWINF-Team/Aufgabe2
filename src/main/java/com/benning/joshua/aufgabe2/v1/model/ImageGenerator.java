package com.benning.joshua.aufgabe2.v1.model;

import com.benning.joshua.aufgabe2.v1.model.data.GenerationData;
import com.benning.joshua.aufgabe2.v1.model.data.GrowSpeedGenerator;
import com.benning.joshua.aufgabe2.v1.model.data.ImageGenData;
import com.benning.joshua.aufgabe2.v2.gif.SequenceWriter;
import lombok.RequiredArgsConstructor;

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

@RequiredArgsConstructor
public class ImageGenerator {
    private final ImageGenData imageGenData;
    private final GrowSpeedGenerator growSpeedGenerator;
    private final GenerationData generationData; // Done

    private List<BufferedImage> images = new ArrayList<>();

    public void generateImage() throws IOException {

        try {
            String d = getProgramPath() + "/out/";
            System.out.println("Making directory at " + d);
            File dir = new File(d);
            if (dir.exists()) System.out.println("Output folder was found");
            if (!dir.mkdir()) System.out.println("Could not generate folder!");
        }
        catch (Exception e) { System.out.println("Exception occured" + e);}


        Canvas canvas = new Canvas(imageGenData.getWidth(), imageGenData.getHeight(), Color.BLACK);
        Random random = new Random();
        Queue<Pixel> pixelsToExpand = new ConcurrentLinkedQueue<>();
        generateStartPoints(canvas, random, pixelsToExpand,  generationData.getInitialSP());
        int sth = 0;
        while (pixelsToExpand.size() != 0 && sth < 50000) {
            if (sth % generationData.getTickDuration() == 0) generateStartPoints(canvas, random, pixelsToExpand, generationData.getSPperTick());
            Pixel poll = pixelsToExpand.poll();
            if (poll != null) poll.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.addAll(canvas.expand(poll));
            sth++;
            if (this.imageGenData.getImageType() == ImageType.GIF) images.add(canvas.render(false, ""));
        }
        if (sth >= 1500) System.out.println("Image generation was stopped as too many iterations (>1500) where necessary to fill the canvas");
        BufferedImage image = canvas.render(true, "");
        if (this.imageGenData.getImageType() == ImageType.GIF) {
            ImageOutputStream output = new FileImageOutputStream(new File("./out/image.gif"));
            SequenceWriter writer = new SequenceWriter(output, image.getType(), 1, true);
            for (BufferedImage bufferedImage : images) {
                writer.writeToSequence(bufferedImage);
            }
        }
    }

    private void generateStartPoints(Canvas canvas, Random random, Queue<Pixel> pixelsToExpand, int count) {
        for (int spawnPoint = 0; spawnPoint < count; spawnPoint++) {
            Pixel startPxl = canvas.getData()[random.nextInt(this.imageGenData.getWidth())][random.nextInt(this.imageGenData.getHeight())];
            if (startPxl.getPixelState() != Pixel.PixelState.EMPTY) {
                spawnPoint--;
                continue;
            }
            int rgbValue = (int) (random.nextFloat(0.0f, 1.0f) * 255);
            startPxl.setColor(new Color(rgbValue, rgbValue, rgbValue));
            startPxl.setGrowSpeed(this.growSpeedGenerator.getRandomSpeed());
            startPxl.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.add(startPxl);
        }
    }

    public static String getProgramPath() {
        String currentdir = System.getProperty("user.dir");
        currentdir = currentdir.replace( "\\", "/" );
        return currentdir;

    }
}

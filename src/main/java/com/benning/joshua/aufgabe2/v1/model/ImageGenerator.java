package com.benning.joshua.aufgabe2.v1.model;

import com.benning.joshua.aufgabe2.v1.gif.SequenceWriter;
import com.benning.joshua.aufgabe2.v1.model.data.GenerationData;
import com.benning.joshua.aufgabe2.v1.model.data.GrowSpeedGenerator;
import com.benning.joshua.aufgabe2.v1.model.data.ImageGenData;
import com.benning.joshua.aufgabe2.v1.model.data.ImageType;
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
        Long start = System.currentTimeMillis();
        try {
            String d = getProgramPath() + "/out/";
            File dir = new File(d);
            if (dir.exists()) System.out.println("Output folder was found");
            if (!dir.mkdir()) System.out.println("Could not generate output folder!");
        }
        catch (Exception e) { System.out.println("Exception occured" + e);}


        Canvas canvas = new Canvas(imageGenData.getWidth(), imageGenData.getHeight(), Color.BLACK);
        Random random = new Random();
        Queue<Pixel> pixelsToExpand = new ConcurrentLinkedQueue<>(); // Pixels that can grow
        generateStartPoints(canvas, random, pixelsToExpand,  generationData.getInitialSP());
        int iterationCount = 0; // Counting iterations
        while (pixelsToExpand.size() != 0 && iterationCount < 1_000_000) {
            if (iterationCount % generationData.getTickDuration() == 0) generateStartPoints(canvas, random, pixelsToExpand, generationData.getSPperTick());
            Pixel poll = pixelsToExpand.poll();
            if (poll != null) poll.setPixelState(Pixel.PixelState.INACTIVE);
            pixelsToExpand.addAll(canvas.expand(poll));
            iterationCount++;

            // Saving a frame every 1000 iterations if a GIF should be generated
            if (this.imageGenData.getImageType() == ImageType.GIF && iterationCount % 1000 == 0) {
                images.add(canvas.render(false, ""));
            }
        }

        BufferedImage image = canvas.render(true, this.imageGenData.getTargetName());
        if (this.imageGenData.getImageType() == ImageType.GIF) {
            ImageOutputStream output = new FileImageOutputStream(new File("./out/" + this.imageGenData.getTargetName() + ".gif"));
            SequenceWriter writer = new SequenceWriter(output, image.getType(), 1, true);
            for (BufferedImage bufferedImage : images) {
                writer.writeToSequence(bufferedImage);
            }
        }

        System.out.println("Generation took " + ((System.currentTimeMillis() - start) / 1000.0) + " seconds");
    }

    private void generateStartPoints(Canvas canvas, Random random, Queue<Pixel> pixelsToExpand, int count) {
        for (int spawnPoint = 0; spawnPoint < count; spawnPoint++) {
            int w = random.nextInt(this.imageGenData.getWidth());
            int h = random.nextInt(this.imageGenData.getHeight());
            Pixel startPxl = canvas.getData()[w][h];
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

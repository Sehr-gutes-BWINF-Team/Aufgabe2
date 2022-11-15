package com.benning.joshua.aufgabe2.v1.model;

import com.benning.joshua.aufgabe2.v1.model.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public final class ImageData {
    private final Pixel[][] data;
    private final int width;
    private final int height;

    public ImageData(int width, int height, Color defaultColor) {
        this.data = new Pixel[width][height];
        this.width = width;
        this.height = height;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.data[x][y] = new Pixel(x, y, Pixel.PixelState.EMPTY, defaultColor);
            }
        }
    }

    public Pixel[][] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public HashMap<Pixel.Direction, Pixel> getNeumannNeighbours(Pixel pixel) {

        HashMap<Pixel.Direction, Pixel> neumannNeighbours = new HashMap<>();

        int x = pixel.getX();
        int y = pixel.getY();

        if (x + 1 < this.width) neumannNeighbours.put(Pixel.Direction.EAST, this.data[x + 1][y]);
        else neumannNeighbours.put(Pixel.Direction.EAST, null);
        if (x - 1 >= 0) neumannNeighbours.put(Pixel.Direction.WEST, this.data[x - 1][y]);
        else neumannNeighbours.put(Pixel.Direction.WEST, null);
        if (y + 1 < this.height) neumannNeighbours.put(Pixel.Direction.NORTH, this.data[x][y + 1]);
        else neumannNeighbours.put(Pixel.Direction.NORTH, null);
        if (y - 1 >= 0) neumannNeighbours.put(Pixel.Direction.SOUTH, this.data[x][y - 1]);
        else neumannNeighbours.put(Pixel.Direction.SOUTH, null);

        return neumannNeighbours;
    }

    public HashMap<Pixel.Direction, Pixel> getEmptyNeighbours(Pixel pixel) {
        HashMap<Pixel.Direction, Pixel> neumannNeighbours = getNeumannNeighbours(pixel);
        HashMap<Pixel.Direction, Pixel> emptyNeumannNeighbours = new HashMap<>(neumannNeighbours);
        for (Map.Entry<Pixel.Direction, Pixel> directionPixelEntry : neumannNeighbours.entrySet()) {
            Pixel value = directionPixelEntry.getValue();
            if (value != null && value.getPixelState() != Pixel.PixelState.EMPTY) {
                emptyNeumannNeighbours.remove(directionPixelEntry.getKey());
            }
        }
        return emptyNeumannNeighbours;
    }

    public List<Pixel> expand(Pixel pixel) {
        List<Pixel> newPixel = new ArrayList<>();
        Random random = new Random();
        for (Pixel.Direction value : Pixel.Direction.values()) {
            Pixel neumannNeighbour = this.getEmptyNeighbours(pixel).get(value);
            if (neumannNeighbour != null) {
                if (random.nextFloat() < pixel.getGrowSpeed().get(value)) {
                    neumannNeighbour.setColor(pixel.getColor());
                    neumannNeighbour.setPixelState(Pixel.PixelState.ACTIVE);
                    neumannNeighbour.setGrowSpeed(pixel.getGrowSpeed());
                    newPixel.add(neumannNeighbour);
                } else { // Pixel would be fillable, but was not due to the growSpeed
                    newPixel.add(pixel); // Pixel will be expanded another time
                }
            }
        }
        return newPixel;
    }

    public BufferedImage render(int debugStep) {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        int total = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (this.data[x][y].getPixelState() == Pixel.PixelState.ACTIVE) {
                    image.setRGB(x, y, this.data[x][y].getColor().getRGB());
                    total++;
                } else if (this.data[x][y].getPixelState() == Pixel.PixelState.INACTIVE) {
                    Color pixelColor = this.data[x][y].getColor();
                    image.setRGB(x, y, new Color(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()).getRGB());
                    total++;
                }
            }
        }
        try {
            ImageIO.write(image, "PNG", new File("out/debug" + debugStep + ".png"));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ImageData) obj;
        return Objects.equals(this.data, that.data) &&
                this.width == that.width &&
                this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, width, height);
    }

    @Override
    public String toString() {
        return "ImageData[" +
                "data=" + data + ", " +
                "width=" + width + ", " +
                "height=" + height + ']';
    }

}

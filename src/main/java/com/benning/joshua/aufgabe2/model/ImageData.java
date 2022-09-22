package com.benning.joshua.aufgabe2.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<Pixel> getNeumannNeighbours(Pixel pixel) {

        List<Pixel> neumannNeighbours = new ArrayList<>();

        int x = pixel.getX();
        int y = pixel.getY();

        if (x + 1 < this.width) neumannNeighbours.add(this.data[x + 1][y]);
        if (x - 1 >= 0) neumannNeighbours.add(this.data[x - 1][y]);
        if (y + 1 < this.height) neumannNeighbours.add(this.data[x][y + 1]);
        if (y - 1 >= 0) neumannNeighbours.add(this.data[x][y - 1]);

        return neumannNeighbours;
    }

    public List<Pixel> expand(Pixel pixel) { // Von Neumann Nachbarschaft
        List<Pixel> newPixel = new ArrayList<>();
        for (Pixel neumannNeighbour : this.getNeumannNeighbours(pixel)) {
            if (neumannNeighbour.getPixelState() == Pixel.PixelState.EMPTY) {
                neumannNeighbour.setColor(pixel.getColor());
                neumannNeighbour.setPixelState(Pixel.PixelState.ACTIVE);
                newPixel.add(neumannNeighbour);
            }
        }
        return newPixel;
    }

    public void render(int debugStep) {
        BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        int total = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                image.setRGB(x, y, this.data[x][y].getColor().getRGB());
                total++;
            }
        }
        try {
            ImageIO.write(image, "PNG", new File("out/debug" + debugStep + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
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

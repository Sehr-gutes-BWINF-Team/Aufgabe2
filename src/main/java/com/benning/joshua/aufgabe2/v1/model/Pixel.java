package com.benning.joshua.aufgabe2.v1.model;

import com.benning.joshua.aufgabe2.v1.model.data.SpeedData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Pixel {
    private final int x;
    private final int y;
    private PixelState pixelState = PixelState.EMPTY;
    private Color color;
    private SpeedData growSpeed;

    public Pixel(int x, int y, PixelState pixelState, Color color) {
        this.x = x;
        this.y = y;
        this.pixelState = pixelState;
        this.color = color;
        this.growSpeed = new SpeedData(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public Pixel(int x, int y, PixelState pixelState, Color color, SpeedData growSpeed) {
        this.x = x;
        this.y = y;
        this.pixelState = pixelState;
        this.color = color;
        this.growSpeed = growSpeed;
    }

    public enum PixelState {
        ACTIVE,
        INACTIVE,
        EMPTY
    }

    @AllArgsConstructor
    @Getter
    public enum Direction {
        NORTH(0),
        SOUTH(1),
        WEST(2),
        EAST(3);
        private final int speedIndex;
    }
}

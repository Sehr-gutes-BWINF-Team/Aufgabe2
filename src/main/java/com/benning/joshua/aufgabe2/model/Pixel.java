package com.benning.joshua.aufgabe2.model;

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
    private GrowSpeed growSpeed;

    public Pixel(int x, int y, PixelState pixelState, Color color) {
        this.x = x;
        this.y = y;
        this.pixelState = pixelState;
        this.color = color;
        this.growSpeed = new GrowSpeed(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public Pixel(int x, int y, PixelState pixelState, Color color, GrowSpeed growSpeed) {
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

package com.benning.joshua.aufgabe2.v2.model;

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

    public Pixel(int x, int y, PixelState pixelState, Color color) {
        this.x = x;
        this.y = y;
        this.pixelState = pixelState;
        this.color = color;
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

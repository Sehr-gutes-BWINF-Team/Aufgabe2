package com.benning.joshua.aufgabe2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
public class Pixel {
    private final int x;
    private final int y;
    private PixelState pixelState = PixelState.EMPTY;
    private Color color;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
    }

    public enum PixelState {
        ACTIVE,
        INACTIVE,
        EMPTY
    }
}

package com.benning.joshua.aufgabe2.v2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private float x;
    private float y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return Math.round(x);
    }

    public int getY() {
        return Math.round(y);
    }
    public float getAbsoluteX() {
        return x;
    }

    public float getAbsoluteY() {
        return y;
    }

    public void addX(float n) {
        this.x += n;
    }

    public void addY(float n) {
        this.y += n;
    }

}

package com.benning.joshua.aufgabe2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

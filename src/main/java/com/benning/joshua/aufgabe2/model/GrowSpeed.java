package com.benning.joshua.aufgabe2.model;

import lombok.Data;

@Data
public class GrowSpeed {
    private final float[] speeds;

    public GrowSpeed(float speedN, float speedS, float speedW, float speedE) {
        this.speeds = new float[]{speedN, speedS, speedW, speedE};
    }

    public float get(Pixel.Direction direction) {
        return this.speeds[direction.getSpeedIndex()];
    }
}

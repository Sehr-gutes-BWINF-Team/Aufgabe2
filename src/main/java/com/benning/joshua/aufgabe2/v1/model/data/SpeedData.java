package com.benning.joshua.aufgabe2.v1.model.data;

import lombok.Data;

@Data
public class SpeedData {
    private final float[] speeds;

    public SpeedData(float speedN, float speedS, float speedW, float speedE) {
        this.speeds = new float[]{speedN, speedS, speedW, speedE};
    }

    public float get(com.benning.joshua.aufgabe2.v1.model.Pixel.Direction direction) {
        return this.speeds[direction.getSpeedIndex()];
    }
}

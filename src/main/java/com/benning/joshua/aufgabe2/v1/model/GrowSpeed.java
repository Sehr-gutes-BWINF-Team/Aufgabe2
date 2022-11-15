package com.benning.joshua.aufgabe2.v1.model;

import com.benning.joshua.aufgabe2.v1.model.Pixel;
import lombok.Data;

@Data
public class GrowSpeed {
    private final float[] speeds;

    public GrowSpeed(float speedN, float speedS, float speedW, float speedE) {
        this.speeds = new float[]{speedN, speedS, speedW, speedE};
    }

    public float get(com.benning.joshua.aufgabe2.v1.model.Pixel.Direction direction) {
        return this.speeds[direction.getSpeedIndex()];
    }
}

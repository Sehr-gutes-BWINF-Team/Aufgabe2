package com.benning.joshua.aufgabe2.v1.model.data;

import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class SpeedData {
    private final Float[] speeds;

    public SpeedData(float speedN, float speedS, float speedW, float speedE) {
        this.speeds = new Float[]{speedN, speedS, speedW, speedE};
    }

    public float get(com.benning.joshua.aufgabe2.v1.model.Pixel.Direction direction) {
        return this.speeds[direction.getSpeedIndex()];
    }

    public void randomize() {
        List<Float> asList = Arrays.asList(this.speeds);
        Collections.shuffle(asList);
        asList.toArray(speeds);
    }
}

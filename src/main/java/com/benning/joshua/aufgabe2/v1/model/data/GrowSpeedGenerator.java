package com.benning.joshua.aufgabe2.v1.model.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrowSpeedGenerator {
    private Range nRange;
    private Range sRange;
    private Range wRange;
    private Range eRange;

    public GrowSpeedGenerator() {
        nRange = new Range();
        sRange = new Range();
        wRange = new Range();
        eRange = new Range();
    }

    public SpeedData getRandomSpeed() {
        SpeedData speedData = new SpeedData(nRange.get(), sRange.get(), wRange.get(), eRange.get());
        speedData.randomize();
        return speedData;
    }
}

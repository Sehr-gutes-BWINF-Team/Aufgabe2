package com.benning.joshua.aufgabe2.v1.model.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Random;

@RequiredArgsConstructor
@Getter
@Setter
public class Range {
    private float min = 0.0f;
    private float max = 1.0f;

    public float get() {
        return new Random().nextFloat(min, max);
    }
}

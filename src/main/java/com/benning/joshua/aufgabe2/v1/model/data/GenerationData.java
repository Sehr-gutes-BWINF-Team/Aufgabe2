package com.benning.joshua.aufgabe2.v1.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenerationData {
    private Integer initialSP = 50;
    private Integer SPperTick = 10;
    private Integer tickDuration = 1;
}

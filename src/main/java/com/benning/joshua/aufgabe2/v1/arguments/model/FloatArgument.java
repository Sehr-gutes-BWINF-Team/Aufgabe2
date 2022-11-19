package com.benning.joshua.aufgabe2.v1.arguments.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FloatArgument implements Argument{

    private final String name;

    @Override
    public Float parse(String s) {
        return Float.valueOf(s);
    }

    @Override
    public Boolean isApplicable(String name, String content) {
        if (!name.equalsIgnoreCase(this.name)) return false;
        try {
            Float.valueOf(content);
            return true;
        } catch (NumberFormatException ignored) {
            throw new IllegalArgumentException("Argument " + name + " could not be parsed as a Float.");
        }
    }
}

package com.benning.joshua.aufgabe2.v1.arguments.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IntArgument implements Argument{

    private final String name;

    @Override
    public Integer parse(String s) {
        return Integer.valueOf(s);
    }

    @Override
    public Boolean isApplicable(String name, String content) {
        if (!name.equalsIgnoreCase(this.name)) return false;
        try {
            Integer.valueOf(content);
            return true;
        } catch (NumberFormatException ignored) {
            throw new IllegalArgumentException("Argument " + name + " could not be parsed as an Integer.");
        }
    }
}

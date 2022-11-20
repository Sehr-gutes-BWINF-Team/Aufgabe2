package com.benning.joshua.aufgabe2.v1.arguments.model;

import com.benning.joshua.aufgabe2.v1.model.data.ImageType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageTypeArgument implements Argument{

    private final String name;

    @Override
    public ImageType parse(String s) {
        return ImageType.valueOf(s);
    }

    @Override
    public Boolean isApplicable(String name, String content) {
        if (!name.equalsIgnoreCase(this.name)) return false;
        try {
            ImageType.valueOf(content);
            return true;
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Argument " + name + " could not be parsed as an ImageType.");
        }
    }
}

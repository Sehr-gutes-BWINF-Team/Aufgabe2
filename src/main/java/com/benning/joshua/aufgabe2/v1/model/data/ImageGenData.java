package com.benning.joshua.aufgabe2.v1.model.data;

import com.benning.joshua.aufgabe2.v1.model.ImageType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageGenData {
    private int width = 300;
    private int height = 300;
    private ImageType imageType = ImageType.PNG;

    @Override
    public String toString() {
        return "ImageGenData{" +
                "width=" + width +
                ", height=" + height +
                ", imageType=" + imageType +
                '}';
    }
}

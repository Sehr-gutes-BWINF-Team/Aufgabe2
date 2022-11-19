package com.benning.joshua.aufgabe2.v1.arguments;

import com.benning.joshua.aufgabe2.v1.arguments.model.Argument;
import com.benning.joshua.aufgabe2.v1.arguments.model.FloatArgument;
import com.benning.joshua.aufgabe2.v1.arguments.model.ImageTypeArgument;
import com.benning.joshua.aufgabe2.v1.arguments.model.IntArgument;
import com.benning.joshua.aufgabe2.v1.model.ImageType;
import com.benning.joshua.aufgabe2.v1.model.data.GenerationData;
import com.benning.joshua.aufgabe2.v1.model.data.GrowSpeedGenerator;
import com.benning.joshua.aufgabe2.v1.model.data.ImageGenData;
import lombok.Getter;

import java.util.List;

@Getter
public class ArgumentConverter {

    // Data classes to be generated
    ImageGenData imageGenData = new ImageGenData();
    GrowSpeedGenerator growSpeedGenerator = new GrowSpeedGenerator();
    GenerationData generationData = new GenerationData();

    // General params regarding image export
    private final static String imageWidthArgName = "width";
    private final static String imageHeightArgName = "height";
    private final static String imageTypeArgName = "type";

    // Generation specific params
    private final static String initialSpawnPointArgName = "initalSP";
    private final static String spawnPointPerTickArgName = "SPperTick";
    private final static String tickDurationArgName = "tickDuration";

    // Speed params, a lot of speed params
    private final static String minSpeedNArgName = "maxSpeedN";
    private final static String maxSpeedNArgName = "maxSpeedN";
    private final static String minSpeedSArgName = "minSpeedS";
    private final static String maxSpeedSArgName = "maxSpeedS";
    private final static String minSpeedWArgName = "minSpeedW";
    private final static String maxSpeedWArgName = "maxSpeedW";
    private final static String minSpeedEArgName = "minSpeedE";
    private final static String maxSpeedEArgName = "maxSpeedE";

    private final static String minSpeedNSArgName = "minSpeedNS";
    private final static String maxSpeedNSArgName = "maxSpeedNS";
    private final static String minSpeedWEArgName = "minSpeedWE";
    private final static String maxSpeedWEArgName = "maxSpeedWE";

    List<Argument> arguments = List.of(
            new IntArgument(imageWidthArgName),
            new IntArgument(imageHeightArgName),
            new ImageTypeArgument(imageTypeArgName),

            new IntArgument(initialSpawnPointArgName),
            new IntArgument(spawnPointPerTickArgName),
            new IntArgument(tickDurationArgName),

            new FloatArgument(minSpeedNArgName),
            new FloatArgument(minSpeedSArgName),
            new FloatArgument(minSpeedWArgName),
            new FloatArgument(minSpeedEArgName),
            new FloatArgument(maxSpeedNArgName),
            new FloatArgument(maxSpeedSArgName),
            new FloatArgument(maxSpeedWArgName),
            new FloatArgument(maxSpeedEArgName),

            new FloatArgument(minSpeedNSArgName),
            new FloatArgument(maxSpeedNSArgName),
            new FloatArgument(minSpeedWEArgName),
            new FloatArgument(maxSpeedWEArgName)
            );

    public ArgumentConverter(List<String> stringList) {
        for (int i = 0; i < stringList.size(); i+=2) {
            String name = stringList.get(i).replace("-", "");
            String content = stringList.get(i + 1);
            for (Argument argument : arguments) {
                if (argument.isApplicable(name, content)) {
                    Object parse = argument.parse(content);
                    System.out.println(name + " " + parse);

                    // ImageData
                    if (name.equalsIgnoreCase(imageWidthArgName)) this.imageGenData.setWidth((Integer) parse);
                    else if (name.equalsIgnoreCase(imageHeightArgName)) this.imageGenData.setHeight((Integer) parse);
                    else if (name.equalsIgnoreCase(imageTypeArgName)) this.imageGenData.setImageType((ImageType) parse);

                    // GenerationData
                    else if (name.equalsIgnoreCase(initialSpawnPointArgName)) this.generationData.setInitialSP((Integer) parse);
                    else if (name.equalsIgnoreCase(spawnPointPerTickArgName)) this.generationData.setSPperTick((Integer) parse);
                    else if (name.equalsIgnoreCase(tickDurationArgName)) this.generationData.setTickDuration((Integer) parse);

                    // GrowSpeedGenerator
                    else if (name.equalsIgnoreCase(maxSpeedNArgName)) this.growSpeedGenerator.getNRange().setMax((Float) parse);
                    else if (name.equalsIgnoreCase(minSpeedNArgName)) this.growSpeedGenerator.getNRange().setMin((Float) parse);
                    else if (name.equalsIgnoreCase(maxSpeedSArgName)) this.growSpeedGenerator.getSRange().setMax((Float) parse);
                    else if (name.equalsIgnoreCase(minSpeedSArgName)) this.growSpeedGenerator.getSRange().setMin((Float) parse);
                    else if (name.equalsIgnoreCase(maxSpeedWArgName)) this.growSpeedGenerator.getWRange().setMax((Float) parse);
                    else if (name.equalsIgnoreCase(minSpeedWArgName)) this.growSpeedGenerator.getWRange().setMin((Float) parse);
                    else if (name.equalsIgnoreCase(maxSpeedEArgName)) this.growSpeedGenerator.getERange().setMax((Float) parse);
                    else if (name.equalsIgnoreCase(minSpeedEArgName)) this.growSpeedGenerator.getERange().setMin((Float) parse);


                    else if (name.equalsIgnoreCase(minSpeedWEArgName)) {
                        this.growSpeedGenerator.getERange().setMin((Float) parse);
                        this.growSpeedGenerator.getWRange().setMin((Float) parse);
                    }
                    else if (name.equalsIgnoreCase(maxSpeedWEArgName)) {
                        this.growSpeedGenerator.getERange().setMax((Float) parse);
                        this.growSpeedGenerator.getWRange().setMax((Float) parse);
                    }
                    else if (name.equalsIgnoreCase(minSpeedNSArgName)) {
                        this.growSpeedGenerator.getNRange().setMin((Float) parse);
                        this.growSpeedGenerator.getSRange().setMin((Float) parse);
                    }
                    else if (name.equalsIgnoreCase(maxSpeedNSArgName)) {
                        this.growSpeedGenerator.getSRange().setMax((Float) parse);
                        this.growSpeedGenerator.getNRange().setMax((Float) parse);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ArgumentConverter{" +
                "imageGenData=" + imageGenData +
                ", growSpeedGenerator=" + growSpeedGenerator +
                ", generationData=" + generationData +
                ", arguments=" + arguments +
                '}';
    }
}

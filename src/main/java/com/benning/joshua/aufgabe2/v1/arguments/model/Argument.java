package com.benning.joshua.aufgabe2.v1.arguments.model;

public interface Argument {

    Object parse(String content);
    Boolean isApplicable(String name, String content);

}

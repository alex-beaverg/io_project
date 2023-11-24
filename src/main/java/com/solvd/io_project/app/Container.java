package com.solvd.io_project.app;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Text> texts = new ArrayList<>();

    public List<Text> getTexts() {
        return texts;
    }

    public void addTextToList(Text text) {
        texts.add(text);
    }
}

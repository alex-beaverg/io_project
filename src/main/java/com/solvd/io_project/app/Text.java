package com.solvd.io_project.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Text {
    private String content;
    private int numberOfTheUniqueWords;
    private int numberOfTheLetters;
    private String allLetters;
    private final Map<String, Integer> mapOfFoundWords = new HashMap<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumberOfTheUniqueWords() {
        return numberOfTheUniqueWords;
    }

    public void setNumberOfTheUniqueWords(int numberOfTheUniqueWords) {
        this.numberOfTheUniqueWords = numberOfTheUniqueWords;
    }

    public int getNumberOfTheLetters() {
        return numberOfTheLetters;
    }

    public void setNumberOfTheLetters(int numberOfTheLetters) {
        this.numberOfTheLetters = numberOfTheLetters;
    }

    public String getAllLetters() {
        return allLetters;
    }

    public void setAllLetters(String allLetters) {
        this.allLetters = allLetters;
    }

    public Map<String, Integer> getMapOfFoundWords() {
        return mapOfFoundWords;
    }

    public void addFoundWordToMap(String word, int number) {
        mapOfFoundWords.put(word, number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Text that = (Text) obj;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return content == null ? 0 : content.hashCode();
    }
}

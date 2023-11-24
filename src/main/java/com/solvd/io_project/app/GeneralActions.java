package com.solvd.io_project.app;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RegExUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralActions {
    public static int countTheNumberOfTheUniqueWords(Text text) {
        String newText = StringUtils.lowerCase(text.getContent());
        newText = RegExUtils.removePattern(newText, "[^а-яёa-z\\s]");
        List<String> tempList = new ArrayList<>();
        for (String word : StringUtils.split(newText)) {
            if (!tempList.contains(word)) {
                tempList.add(word);
            }
        }
        int numberOfTheUniqueWords = tempList.size();
        text.setNumberOfTheUniqueWords(numberOfTheUniqueWords);
        return numberOfTheUniqueWords;
    }

    public static int countTheNumberOfTheLetters(Text text) {
        String newText = RegExUtils.removePattern(text.getContent(), "[^А-Яа-яЁёA-Za-z]");
        int numberOfLetters = newText.length();
        text.setNumberOfTheLetters(numberOfLetters);
        return numberOfLetters;
    }

    public static String getAllLettersFromText(Text text) {
        String newText = StringUtils.upperCase(text.getContent());
        newText = RegExUtils.removePattern(newText, "[^А-ЯЁA-Z]");
        List<Character> tempList = new ArrayList<>();
        for (char letter : newText.toCharArray()) {
            tempList.add(letter);
        }
        newText = StringUtils.joinWith(" ", tempList.toArray());
        text.setAllLetters(newText);
        return newText;
    }

    public static int findMatchesInText(Text text, String word) {
        String newText = StringUtils.lowerCase(text.getContent());
        newText = RegExUtils.removePattern(newText, "[^а-яёa-z\\s]");
        List<String> tempList = new ArrayList<>(Arrays.asList(StringUtils.split(newText)));
        int numberOfWords = 0;
        for (String item : tempList) {
            if (item.equals(word)) {
                numberOfWords++;
            }
        }
        text.addFoundWordToMap(word, numberOfWords);
        return numberOfWords;
    }
}

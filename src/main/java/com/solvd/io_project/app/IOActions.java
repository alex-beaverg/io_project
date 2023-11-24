package com.solvd.io_project.app;

import com.solvd.io_project.console_menu.RequestMethods;
import com.solvd.io_project.util.custom_exceptions.EmptyInputException;
import com.solvd.io_project.util.custom_exceptions.ShortPhraseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class IOActions {
    private static final Logger LOGGER = LogManager.getLogger(IOActions.class);

    public static String readTextFromFile() {
        String path = "src/main/java/com/solvd/io_project/data/text.txt";
        try {
            return FileUtils.readFileToString(new File(path),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("[IOException]: You have a problem with reading text from the file [" + path + "]!");
            return null;
        }
    }

    public static String readTextFromConsole() {
        String text;
        do {
            try {
                text = RequestMethods.requestingInfoString("\nEnter your text (more than 20 letters): ");
                break;
            } catch (EmptyInputException | ShortPhraseException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        return text;
    }

    public static void writeResultsToFile(Container container) {
        String path = "src/main/java/com/solvd/io_project/data/results.txt";
        StringBuilder data = new StringBuilder();
        int index = 1;
        if (container.getTexts().size() > 0) {
            for (Text text : container.getTexts()) {
                data.append("= = = = =\n[TEXT ")
                        .append(index)
                        .append("]\n[ORIGINAL TEXT]:\n\t")
                        .append(StringUtils.replace(text.getContent(), "\n", "\n\t"))
                        .append("\n[NUMBER OF THE UNIQUE WORDS]: ")
                        .append(text.getNumberOfTheUniqueWords() == 0 ? "Calculation was not done" : text.getNumberOfTheUniqueWords())
                        .append("\n[LETTERS]: ")
                        .append(text.getAllLetters() == null ? "Calculation was not done" : text.getAllLetters())
                        .append("\n[NUMBER OF THE LETTERS]: ")
                        .append(text.getNumberOfTheLetters() == 0 ? "Calculation was not done" : text.getNumberOfTheLetters())
                        .append("\n[SEARCH BY WORDS]: ");
                if (text.getMapOfFoundWords().size() > 0) {
                    for (Map.Entry<String, Integer> entry : text.getMapOfFoundWords().entrySet()) {
                        data.append("\n\tWORD: ")
                                .append(entry.getKey())
                                .append("\n\tMATCHES: ")
                                .append(entry.getValue());
                    }
                } else {
                    data.append("Search was not done");
                }
                data.append("\n= = = = =\n\n");
                index++;
            }
        } else {
            data.append("No text was received for processing");
        }
        try {
            FileUtils.writeStringToFile(new File(path),
                    StringUtils.trim(data.toString()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("[IOException]: You have a problem with writing text into the file [" + path + "]!");
        }
    }
}
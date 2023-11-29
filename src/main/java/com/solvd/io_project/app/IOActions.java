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
    private static final String PATH_TO_READ_FILE = "src/main/java/com/solvd/io_project/data/text.txt";
    private static final String PATH_TO_WRITE_FILE = "src/main/java/com/solvd/io_project/data/results.txt";
    private static final String NO_ACTION = "The action was not done";

    public static String readTextFromFile() {
        try {
            return FileUtils.readFileToString(new File(PATH_TO_READ_FILE), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("[IOException]: You have a problem with reading text from the file (" + PATH_TO_READ_FILE + ")!");
            return null;
        }
    }

    public static String readTextFromConsole() {
        String text;
        do {
            try {
                text = RequestMethods.infoStringRequest("\nEnter your text (more than 20 letters): ");
                break;
            } catch (EmptyInputException | ShortPhraseException e) {
                LOGGER.error(e.getMessage());
            }
        } while (true);
        return text;
    }

    public static void writeResultsToFile(Container container) {
        StringBuilder data = new StringBuilder();
        int index = 1;
        if (container.getTexts().size() > 0) {
            for (Text text : container.getTexts()) {
                data.append("= = = = =\n[TEXT ")
                        .append(index)
                        .append("]\n[ORIGINAL TEXT]:\n\t")
                        .append(StringUtils.replace(text.getContent(), "\n", "\n\t"))
                        .append("\n[NUMBER OF THE UNIQUE WORDS]: ")
                        .append(text.getNumberOfTheUniqueWords() == 0 ? NO_ACTION : text.getNumberOfTheUniqueWords())
                        .append("\n[LETTERS]: ")
                        .append(text.getAllLetters() == null ? NO_ACTION : text.getAllLetters())
                        .append("\n[NUMBER OF THE LETTERS]: ")
                        .append(text.getNumberOfTheLetters() == 0 ? NO_ACTION : text.getNumberOfTheLetters())
                        .append("\n[SEARCH BY WORDS]: ");
                if (text.getMapOfFoundWords().size() > 0) {
                    for (Map.Entry<String, Integer> entry : text.getMapOfFoundWords().entrySet()) {
                        data.append("\n\tWORD: ")
                                .append(entry.getKey())
                                .append("\n\tMATCHES: ")
                                .append(entry.getValue());
                    }
                } else {
                    data.append(NO_ACTION);
                }
                data.append("\n= = = = =\n\n");
                index++;
            }
        } else {
            data.append("The text was not received for processing");
        }
        try {
            FileUtils.writeStringToFile(new File(PATH_TO_WRITE_FILE), StringUtils.trim(data.toString()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("[IOException]: You have a problem with writing text into the file (" + PATH_TO_WRITE_FILE + ")!");
        }
    }
}
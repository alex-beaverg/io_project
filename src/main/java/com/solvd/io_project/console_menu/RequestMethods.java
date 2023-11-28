package com.solvd.io_project.console_menu;

import static com.solvd.io_project.util.Printers.*;

import com.solvd.io_project.util.custom_exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class RequestMethods {
    private static final Scanner scanner = new Scanner(System.in);

    public static int menuItemRequest(String text, int menuItemsNumber)
            throws EmptyInputException, NumberFormatException, MenuItemOutOfBoundsException {
        PRINT.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        int numberFromAnswer = Integer.parseInt(answer);
        if (numberFromAnswer < 1 || numberFromAnswer > menuItemsNumber) {
            throw new MenuItemOutOfBoundsException("[MenuItemNumberOutOfBoundsException]: Entered data " +
                    "must be equal to some menu item!");
        }
        return numberFromAnswer;
    }

    public static String infoStringRequest(String text) throws EmptyInputException, ShortPhraseException {
        PRINT.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        if (answer.length() <= 20) {
            throw new ShortPhraseException("[ShortPhraseException]: Entered data must be longer than 20 letters");
        }
        return answer;
    }

    public static String searchWordRequest(String text) throws EmptyInputException, WordToFindException {
        PRINT.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        if (!answer.matches("^ *[a-zA-Zа-яёА-ЯЁ]+[a-zA-Zа-яёА-ЯЁ]+ *$")) {
            throw new WordToFindException("[WordToFindException]: Entered data is incorrect! Enter the word!");
        }
        return StringUtils.lowerCase(StringUtils.trim(answer));
    }

    static void closeScanner() {
        scanner.close();
    }
}

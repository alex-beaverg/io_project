package com.solvd.io_project.console_menu;

import com.solvd.io_project.app.*;
import com.solvd.io_project.console_menu.menu_enums.*;
import com.solvd.io_project.util.custom_exceptions.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.solvd.io_project.util.Printers.*;

public class ConsoleMenu {
    private final Container container = new Container();
    private static final Logger LOGGER = LogManager.getLogger(ConsoleMenu.class);

    public void runApp() {
        PRINT2LN.info("Input/Output project application");
        runMainMenu();
    }

    private ConsoleMenu tearDown() {
        RequestMethods.closeScanner();
        PRINTLN.info("Good bye!");
        return null;
    }

    private int drawAnyMenuAndChooseMenuItem(String title, IMenu[] menuItems) {
        int index = 1;
        PRINT2LN.info(title);
        for (IMenu item : menuItems) {
            PRINTLN.info("[" + index + "] - " + item.getTitle());
            index++;
        }
        int answer;
        do {
            try {
                answer = RequestMethods.menuItemRequest("Enter the menu item number: ", index - 1);
                break;
            } catch (EmptyInputException | MenuItemOutOfBoundsException e) {
                LOGGER.error(e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.error("[NumberFormatException]: Entered data is not a number!");
            }
        } while (true);
        return answer;
    }

    private ConsoleMenu runMainMenu() {
        String content;
        Text text = new Text();
        int answer = drawAnyMenuAndChooseMenuItem("Main menu:", MainMenu.values());
        switch (answer) {
            case (1) -> {
                content = IOActions.readTextFromFile();
                if (content == null) {
                    return runMainMenu();
                } else {
                    text.setContent(content);
                    container.addTextToList(text);
                    PRINT2LN.info("[THIS TEXT WAS GOTTEN FROM FILE]:");
                    PRINTLN.info("\t" + StringUtils.replace(content, "\n", "\n\t"));
                    return runActionsMenu(text);
                }
            }
            case (2) -> {
                content = IOActions.readTextFromConsole();
                text.setContent(content);
                container.addTextToList(text);
                PRINT2LN.info("[THIS TEXT WAS GOTTEN FROM CONSOLE]:");
                PRINTLN.info("\t" + StringUtils.replace(content, "\n", "\n\t"));
                return runActionsMenu(text);
            }
            case (3) -> {
                return tearDown();
            }
            default -> {
                IOActions.writeResultsToFile(container);
                return tearDown();
            }
        }
    }

    private ConsoleMenu runActionsMenu(Text text) {
        int answer = drawAnyMenuAndChooseMenuItem("Actions Menu:", ActionsMenu.values());
        switch (answer) {
            case (1) -> {
                PRINT2LN.info("[COUNTING THE NUMBER OF THE UNIQUE WORDS]");
                PRINTLN.info("[ORIGINAL TEXT]:\n\t" + StringUtils.replace(text.getContent(), "\n", "\n\t"));
                PRINTLN.info("[NUMBER OF THE UNIQUE WORDS]: " + GeneralActions.countTheNumberOfTheUniqueWords(text));
                return runActionsMenu(text);
            }
            case (2) -> {
                PRINT2LN.info("[COUNTING THE NUMBER OF THE LETTERS]");
                PRINTLN.info("[ORIGINAL TEXT]:\n\t" + StringUtils.replace(text.getContent(), "\n", "\n\t"));
                PRINTLN.info("[LETTERS IN TEXT]: " + GeneralActions.getAllLettersFromText(text));
                PRINTLN.info("[NUMBER OF THE LETTERS]: " + GeneralActions.countTheNumberOfTheLetters(text));
                return runActionsMenu(text);
            }
            case (3) -> {
                String wordToSearch;
                do {
                    try {
                        wordToSearch = RequestMethods.searchWordRequest("\nEnter the word you want to find: ");
                        break;
                    } catch (EmptyInputException | WordToFindException e) {
                        LOGGER.error(e.getMessage());
                    }
                } while (true);
                PRINT2LN.info("[SEARCH BY WORDS]");
                PRINTLN.info("[ORIGINAL TEXT]:\n\t" + StringUtils.replace(text.getContent(), "\n", "\n\t"));
                PRINTLN.info("[SEARCH BY WORD]: ");
                PRINTLN.info("\tWORD: " + wordToSearch);
                PRINTLN.info("\tMATCHES: " + GeneralActions.findMatchesInText(text, wordToSearch));
                return runActionsMenu(text);
            }
            case (4) -> {
                return runMainMenu();
            }
            default -> {
                IOActions.writeResultsToFile(container);
                return tearDown();
            }
        }
    }
}

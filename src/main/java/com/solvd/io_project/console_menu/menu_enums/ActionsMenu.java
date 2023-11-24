package com.solvd.io_project.console_menu.menu_enums;

public enum ActionsMenu implements IMenu {
    UNIQUE_WORDS("Count the number of the unique words in the text"),
    LETTERS_NUMBER("Count the number of the letters in the text"),
    WORD_SEARCH("Find the word in the text"),
    MAIN_MENU("Return to the Main menu"),
    EXIT("Save results to the file 'data/results.txt' and exit");

    private final String title;

    ActionsMenu(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

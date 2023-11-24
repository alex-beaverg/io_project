package com.solvd.io_project.console_menu.menu_enums;

public enum MainMenu implements IMenu {
    READ_FILE("Get text from the file 'data/text.txt'"),
    READ_CONSOLE("Get text from console"),
    EXIT("Exit without saving"),
    SAVE_AND_EXIT("Save results to the file 'data/results.txt' and exit");

    private final String title;

    MainMenu(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

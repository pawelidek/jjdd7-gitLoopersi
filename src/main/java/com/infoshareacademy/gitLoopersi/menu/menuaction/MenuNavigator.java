package com.infoshareacademy.gitLoopersi.menu.menuaction;

import java.util.Scanner;

public class MenuNavigator {

    private String page = "m";
    private boolean exit = false;

    void changeMenuPage() {

        Scanner scanner = new Scanner(System.in);
        String readValue = scanner.nextLine();

        if (readValue.equals("0") && page.length() > 1) {
            this.page = this.page.substring(0, this.page.length() - 1);
        } else if (readValue.equals("exit")) {
            this.exit = true;
        } else if (readValue.length() == 1) {
            this.page += readValue;
        }
    }

    public void returnToPreviousPage() {
        this.page = this.page.substring(0, this.page.length() - 1);
    }

    boolean getExit() {
        return exit;
    }

    String getPage() {
        return page;
    }
}
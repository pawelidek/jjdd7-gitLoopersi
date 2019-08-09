package com.infoshareacademy.jjdd7.menu.menuaction;

import java.util.Scanner;

public class MenuNavigator {

    private String page = "m";

    public MenuNavigator() {
    }

    public MenuNavigator(String page) {
        this.page = page;
    }

    void changeMenuPage() {

        Scanner scanner = new Scanner(System.in);
        String readValue = scanner.nextLine();
        if (!readValue.equals("0")) {
            this.page += readValue;
        } else if (this.page.equals("m")) {
            System.out.println("");
        } else {
            this.page = this.page.substring(0, this.page.length() - 1);
        }

    }

    void returnToPreviousPage() {
        this.page = this.page.substring(0, this.page.length() - 1);
    }

    String getPage() {
        return page;
    }
}
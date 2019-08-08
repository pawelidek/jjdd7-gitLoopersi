package com.infoshareacademy.jjdd7.menu.menuaction;

import java.util.Scanner;

public class MenuNavigator {

    private String page = "m";

    public MenuNavigator() {

    }

    public MenuNavigator(String page) {
        this.page = page;
    }

    public void changeMenuPage() {

        Scanner scanner = new Scanner(System.in);
        String readValue = scanner.nextLine();
        if (!readValue.equals("0")) {
            this.page += readValue;
        } else {
            this.page = this.page.substring(0, this.page.length() - 1);
        }

    }

    public void returnToPreviousPage() {

        this.page = this.page.substring(0, this.page.length() - 1);
    }

    public String getPage() {
        return page;
    }
}
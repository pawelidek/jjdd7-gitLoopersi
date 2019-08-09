package com.infoshareacademy.jjdd7;

import com.infoshareacademy.jjdd7.menu.menuaction.MenuAction;
import com.infoshareacademy.jjdd7.menu.menuprint.PrintTitle;

public class App {
    public static void main(String[] args) {

        ParserImpl parser = new ParserImpl("HolidaysApi.json");
        for (Holiday holiday : parser.getListOfHolidays()) {
            System.out.println(holiday);
        }

        MenuAction menuAction = new MenuAction();
        PrintTitle printTitle = new PrintTitle();

        printTitle.doAction();
        menuAction.doMenuAction();
    }
}
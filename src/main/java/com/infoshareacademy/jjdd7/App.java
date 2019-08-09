package com.infoshareacademy.jjdd7;

import com.infoshareacademy.jjdd7.menu.menuaction.MenuAction;
import com.infoshareacademy.jjdd7.menu.menuprint.TitlePrinter;

public class App {
    public static void main(String[] args) {

//        Parser parser = new ParserImpl("HolidaysApi.json");

        MenuAction menuAction = new MenuAction();
        TitlePrinter titlePrinter = new TitlePrinter();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        titlePrinter.doAction();
        menuAction.doMenuAction();
    }
}
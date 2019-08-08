package com.infoshareacademy.jjdd7.menu;

import com.infoshareacademy.jjdd7.menu.menuaction.MenuAction;
import com.infoshareacademy.jjdd7.menu.menuaction.MenuNavigator;
import com.infoshareacademy.jjdd7.menu.menuprint.PrintTitle;

import java.util.Optional;

public class App {
    public static void main(String[] args) {

        MenuAction menuAction = new MenuAction();
        PrintTitle printTitle = new PrintTitle();

        printTitle.doAction();
        menuAction.doMenuAction();
    }
}
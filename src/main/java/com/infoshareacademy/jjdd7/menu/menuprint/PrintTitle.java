package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintTitle implements Menu {

    @Override
    public void doAction() {
        System.out.println("\nWelcome to Employee Vacation Calendar!");
        System.out.println("\nType 'exit' to close the app in every moment.\n");
    }
}
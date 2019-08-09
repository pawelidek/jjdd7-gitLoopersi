package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintHeader implements Menu {

    @Override
    public void doAction() {
        System.out.println("Main menu:");
        System.out.println("1. Workers list");
        System.out.println("2. Vacation");
        System.out.println("3. Search");
        System.out.println("4. Configuration");
    }
}

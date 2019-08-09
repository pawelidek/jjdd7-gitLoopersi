package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintVacation implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Add vacation");
        System.out.println("2. Cancel vacation");
        System.out.println("0. Return");
    }
}

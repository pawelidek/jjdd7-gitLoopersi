package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintHoliday implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Find holiday by name");
        System.out.println("2. Enter date range");
        System.out.println("0. Return");
    }
}

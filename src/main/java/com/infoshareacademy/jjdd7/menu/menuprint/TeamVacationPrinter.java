package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class TeamVacationPrinter implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Find team");
        System.out.println("2. Enter date range");
        System.out.println("0. Return");
    }
}

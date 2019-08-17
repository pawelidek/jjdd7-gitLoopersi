package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class EmployeesVacationPrinter implements Menu {

    @Override
    public void doAction() {
        System.out.println("Vacation search engine for a given employee.");
        System.out.println("\n1. Find worker");
        System.out.println("2. Enter date range");
        System.out.println("0. Return");
        System.out.println("Type \"exit\" to close the app");
    }
}

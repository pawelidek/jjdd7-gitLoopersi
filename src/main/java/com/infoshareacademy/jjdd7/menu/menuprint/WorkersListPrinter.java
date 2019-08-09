package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class WorkersListPrinter implements Menu {

    @Override
    public void doAction() {
        System.out.println("Workers list: [name, team]");
        System.out.println("1. Arek Dąbkowski, gitLoopersi");
        System.out.println("2. Maciej Dzieciuch, gitLoopersi");
        System.out.println("3. Paweł Idek, gitLoopersi");
        System.out.println("4. Marek Sitarski, gitLoopersi");
        System.out.println("\n1. Add worker");
        System.out.println("2. Delete worker");
        System.out.println("0. Return");
    }
}
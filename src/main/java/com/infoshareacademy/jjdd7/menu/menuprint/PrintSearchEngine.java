package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintSearchEngine implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Święto");
        System.out.println("2. Urlop pracownika");
        System.out.println("3. Urlopy zespołu");
        System.out.println("0. Powrót");
    }
}

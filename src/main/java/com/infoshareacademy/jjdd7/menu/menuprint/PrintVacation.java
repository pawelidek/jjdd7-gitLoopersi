package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintVacation implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Dodaj urlop");
        System.out.println("2. Anuluj urlop");
        System.out.println("0. Powrót");
    }
}

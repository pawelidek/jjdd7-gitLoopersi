package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintWorkerVacation implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Szukaj pracownika");
        System.out.println("2. Podaj zakres dat");
        System.out.println("0. Powrót");
    }
}

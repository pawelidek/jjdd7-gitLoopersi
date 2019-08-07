package com.infoshareacademy.jjdd7.menu;

public class PrintConfigurations implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Importuj ustawienia");
        System.out.println("2. Zmień format wyświetlanej daty");
        System.out.println("0. Powrót");
    }
}

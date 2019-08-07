package com.infoshareacademy.jjdd7.menu;

public class PrintTeamVacation implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Szukaj zespołu");
        System.out.println("2. Podaj zakres dat");
        System.out.println("0. Powrót");
    }
}

package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintTitle implements Menu {

    @Override
    public void doAction() {
        System.out.println("\nWitaj w Kalendarzu Urlopów!");
        System.out.println("\nAby zamknąć aplikację wpisz 'exit' w dowolnym momencie.");
        System.out.println("\nWybierz opcję z menu:\n");
    }
}
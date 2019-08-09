package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintSearchEngine implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Holiday");
        System.out.println("2. Workers vacation");
        System.out.println("3. Teams vacation");
        System.out.println("0. Return");
    }
}

package com.infoshareacademy.jjdd7.menu.menuaction;

import com.infoshareacademy.jjdd7.menu.MenuTree;
import com.infoshareacademy.jjdd7.menu.menuprint.AlertMessagePrinter;

public class MenuAction {

    private MenuNavigator menuNavigator = new MenuNavigator();
    private MenuTree menuTree = new MenuTree();
    private AlertMessagePrinter alertMessagePrinter = new AlertMessagePrinter();

    public void doMenuAction() {

        while (true) {
            if (menuTree.buildMenuTree().containsKey(menuNavigator.getPage())) {

                if (menuNavigator.getPage().contains(menuTree.buildMenuTree().containsKey(menuNavigator.getPage()) + "exit")) {
                    break;
                }

                menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
                menuNavigator.changeMenuPage();

                System.out.print("\033[H\033[2J");
                System.out.flush();

            } else {

                if (menuNavigator.getPage().contains(menuTree.buildMenuTree().containsKey(menuNavigator.getPage()) + "exit")) {
                    break;
                }
                alertMessagePrinter.doAction();
                /* Petla while wykonuje sie i usuwa ciag znakow ktory nie istnieje w mapie,
                a ktory ktos podal randomowo */
                while (!menuTree.buildMenuTree().containsKey(menuNavigator.getPage())) {
                        menuNavigator.returnToPreviousPage();
                }

                menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
                menuNavigator.changeMenuPage();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
    }
}
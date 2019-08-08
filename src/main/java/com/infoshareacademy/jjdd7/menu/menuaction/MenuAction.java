package com.infoshareacademy.jjdd7.menu.menuaction;

import com.infoshareacademy.jjdd7.menu.MenuTree;
import com.infoshareacademy.jjdd7.menu.menuprint.PrintAlertMessage;

public class MenuAction {

    private MenuNavigator menuNavigator = new MenuNavigator();
    private MenuTree menuTree = new MenuTree();
    private PrintAlertMessage printAlertMessage = new PrintAlertMessage();

    public void doMenuAction() {

        while (true) {
            if (menuTree.buildMenuTree().containsKey(menuNavigator.getPage())) {

                if (menuNavigator.getPage().contains("exit")) {
                    break;
                }

                System.out.println("Lokalizacja: " + menuNavigator.getPage());
                menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
                menuNavigator.changeMenuPage();
                System.out.print("\033[H\033[2J");
                System.out.flush();

            } else {

                if (menuNavigator.getPage().contains("exit")) {
                    break;
                }
                System.out.println("Lokalizacja: " + menuNavigator.getPage());
                printAlertMessage.doAction();
                /* Petla while wykonuje sie i usuwa ciag znakow ktory nie istnieje w mapie,
                a ktory ktos podal randomowo (do poprawy zejscie ponizej jednego znaku poprzez metode returntopreviouspage) */
                while (!menuTree.buildMenuTree().containsKey(menuNavigator.getPage())) {
                    if (menuNavigator.getPage().length() > 1) {
                        menuNavigator.returnToPreviousPage();
                    } else {
                        System.out.println("Jesteś w menu głównym...");
                        break;
                    }
                }

                menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
                menuNavigator.changeMenuPage();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
    }
}
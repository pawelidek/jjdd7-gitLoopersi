package com.infoshareacademy.jjdd7.menu.menuaction;

import com.infoshareacademy.jjdd7.menu.MenuTree;

public class MenuAction {

    MenuNavigator menuNavigator = new MenuNavigator();
    MenuTree menuTree = new MenuTree();

    public void doMenuAction() {

        while (true) {
            if (menuNavigator.getPage().contains("exit")) {
                break;
            }
            System.out.println(menuNavigator.getPage());
            menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
            menuNavigator.changeMenuPage();
        }
    }
}

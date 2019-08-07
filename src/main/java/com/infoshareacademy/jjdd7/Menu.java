package com.infoshareacademy.jjdd7;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {

    public void loadMenu() {
        Submenu submenu = new Submenu();
        MenuPrinter menuPrinter = new MenuPrinter();
        menuPrinter.printTitle();
        menuPrinter.printHeader();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String select = scanner.nextLine();
            if (select.equals("exit")) {
                break;
            }
            switch (select) {
                case "1":
                    menuPrinter.printWorkersList();

                    submenu.workerListMenu();
                    break;
                case "2":
                    menuPrinter.printVacation();

                    submenu.vacationMenu();
                    break;
                case "3":
                    menuPrinter.printSearchEngine();

                    submenu.searchingEngineMenu();
                    break;
                case "4":
                    menuPrinter.printConfigurations();

                    submenu.configurationsMenu();
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printHeader();
            }
        }
    }
}
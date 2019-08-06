package com.infoshareacademy.jjdd7;

import java.util.Scanner;

public class Menu {

    public void showOptions() {
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
                    select = scanner.nextLine();
                    switch (select) {
                        case "1":
                            System.out.println("bla1");
                            break;
                        case "2":
                            System.out.println("bla2");
                            break;
                        default:
                            System.out.println("default");
                    }
                    break;
                case "2":
                    menuPrinter.printVacation();
                    break;
                case "3":
                    menuPrinter.printSearchEngine();
                    break;
                case "4":
                    menuPrinter.printConfigurations();
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printHeader();
            }
        }
    }
}
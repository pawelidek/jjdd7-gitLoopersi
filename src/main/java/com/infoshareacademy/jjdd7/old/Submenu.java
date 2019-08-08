package com.infoshareacademy.jjdd7.old;

import java.util.Scanner;

public class Submenu {

    private String select;

    public void workerListMenu() {

        MenuPrinter menuPrinter = new MenuPrinter();
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            select = scanner.nextLine();
            if (select.equals("exit")) {
                System.exit(0);
            }
            switch (select) {
                case "0":
                    menu.loadMenu();
                    break;
                case "1":
                    System.out.println("Pracownik dodany");
                    break;
                case "2":
                    System.out.println("Pracownik usunięty");
                    break;
                case "exit":
                    select = "-1";
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printWorkersList();
            }
        }
    }

    public void vacationMenu() {

        MenuPrinter menuPrinter = new MenuPrinter();
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            select = scanner.nextLine();
            if (select.equals("exit")) {
                break;
            }
            switch (select) {
                case "0":
                    menu.loadMenu();
                    break;
                case "1":
                    System.out.println("Urlop dodany");
                    break;
                case "2":
                    System.out.println("Urlop anulowany");
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printVacation();
            }
        }
    }

    public void searchingEngineMenu() {

        MenuPrinter menuPrinter = new MenuPrinter();
        SubmenuSearchEngine submenuSearchingEngine = new SubmenuSearchEngine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            select = scanner.nextLine();
            if (select.equals("exit")) {
                break;
            }
            switch (select) {
                case "0":
                    menuPrinter.printHeader();
                    break;
                case "1":
                    menuPrinter.printHoliday();
                    submenuSearchingEngine.holidayMenu();
                    break;
                case "2":
                    menuPrinter.printWorkerVacation();
                    submenuSearchingEngine.workerVacationMenu(select);
                    break;
                case "3":
                    menuPrinter.printTeamVacation();
                    submenuSearchingEngine.teamVacationMenu(select);
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printSearchEngine();
            }
        }
    }
    
    public void configurationsMenu() {

        MenuPrinter menuPrinter = new MenuPrinter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            select = scanner.nextLine();
            if (select.equals("exit")) {
                break;
            }
            switch (select) {
                case "0":
                    menuPrinter.printHeader();
                    break;
                case "1":
                    System.out.println("Import ustawień");
                    break;
                case "2":
                    System.out.println("Zmiana formatu wyświetlanej daty");
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printConfigurations();
            }
        }
    }
}

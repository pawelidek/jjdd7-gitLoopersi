package com.infoshareacademy.jjdd7;

import java.util.Scanner;

public class Submenu {

    public void workerListMenu() {
        MenuPrinter menuPrinter = new MenuPrinter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String select = scanner.nextLine();
            if (select.equals("exit")) {
                break;
            }
            switch (select) {
                case "0":
                    menuPrinter.printHeader();
                    break;
                case "1":
                    System.out.println("Pracownik dodany");
                    break;
                case "2":
                    System.out.println("Pracownik usunięty");
                    break;
                default:
                    menuPrinter.printAlertMessage();
                    menuPrinter.printWorkersList();
            }
            break;
        }
    }

    public void vacationMenu(String select) {
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

    public void searchingEngineMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();
        SubmenuSearchingEngine submenuSearchingEngine = new SubmenuSearchingEngine();
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
    
    public void configurationsMenu(String select) {
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

package com.infoshareacademy.jjdd7;

import java.util.Scanner;

public class SubmenuSearchEngine {

    public void holidayMenu() {
        MenuPrinter menuPrinter = new MenuPrinter();
        Scanner scanner = new Scanner(System.in);

        String select = scanner.nextLine();
        switch (select) {
            case "0":
                menuPrinter.printSearchEngine();
            case "1":
                System.out.println("Tutaj będzie wprowadzana nazwa święta");
                break;
            case "2":
                System.out.println("Tutaj podajemy zakres dat");
                break;
            default:
                menuPrinter.printAlertMessage();
                menuPrinter.printHoliday();
        }
    }

    public void workerVacationMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();

        switch (select) {
            case "0":
                menuPrinter.printSearchEngine();
            case "1":
                System.out.println("Tutaj będzie wyszukiwarka pracownika");
                break;
            case "2":
                System.out.println("Tutaj podajemy zakres dat urlopu");
                break;
            default:
                menuPrinter.printAlertMessage();
                menuPrinter.printWorkerVacation();
        }
    }

    public void teamVacationMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();

        switch (select) {
            case "0":
                menuPrinter.printSearchEngine();
            case "1":
                System.out.println("Tutaj będzie wyszukiwarka zespołu");
                break;
            case "2":
                System.out.println("Tutaj podajemy zakres dat urlopu zespołu");
                break;
            default:
                menuPrinter.printAlertMessage();
                menuPrinter.printTeamVacation();
        }
    }
}

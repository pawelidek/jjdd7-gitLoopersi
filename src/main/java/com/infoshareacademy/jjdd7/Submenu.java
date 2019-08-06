package com.infoshareacademy.jjdd7;

public class Submenu {

    public void workerListMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();

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
    }

    public void vacationMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();

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
        }
    }

    public void searchingEngineMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();

        switch (select) {
            case "0":
                menuPrinter.printHeader();
                break;
            case "1":
                System.out.println("Święto");
                break;
            case "2":
                System.out.println("Urlop pracownika");
                break;
            case "3":
                System.out.println("Urlopy całego zespołu");
                break;
            default:
                menuPrinter.printAlertMessage();
        }
    }
    
    public void configurationsMenu(String select) {
        MenuPrinter menuPrinter = new MenuPrinter();

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

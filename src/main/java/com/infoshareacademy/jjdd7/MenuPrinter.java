package com.infoshareacademy.jjdd7;

public class MenuPrinter {

    public void printTitle() {
        System.out.println("\nWitaj w Kalendarzu Urlopów!");
        System.out.println("\nAby zamknąć aplikację wpisz 'exit' w dowolnym momencie.");
        System.out.println("\nWybierz opcję z menu:\n");
    }

    public void printHeader() {

        System.out.println("1. Lista pracowników");
        System.out.println("2. Urlop");
        System.out.println("3. Wyszukaj");
        System.out.println("4. Konfiguracja");

    }

    public void printWorkersList() {
        System.out.println("\n1. Dodaj pracownika");
        System.out.println("2. Usuń pracownika");
        System.out.println("0. Powrót");
    }

    public void printVacation() {
        System.out.println("\n1. Dodaj urlop");
        System.out.println("2. Anuluj urlop");
        System.out.println("0. Powrót");
    }

    public void printSearchEngine() {
        System.out.println("\n1. Święto");
        System.out.println("2. Urlop pracownika");
        System.out.println("3. Urlopy zespołu");
        System.out.println("0. Powrót");
    }

    public void printConfigurations() {
        System.out.println("\n1. Importuj ustawienia");
        System.out.println("2. Zmień format wyświetlanej daty");
        System.out.println("0. Powrót");
    }

    public void printAlertMessage() {
        System.out.println("\nWYBIERZ POPRAWNĄ OPCJĘ!");
    }
}

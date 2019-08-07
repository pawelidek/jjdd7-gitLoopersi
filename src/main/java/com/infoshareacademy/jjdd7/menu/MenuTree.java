package com.infoshareacademy.jjdd7.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuTree {
    private String page = "0";

    public MenuTree() {

    }

    public MenuTree(String page) {
        this.page = page;
    }

    public void buildMenuTree() {
        Map<String, Menu> map = new HashMap<>();

        PrintTitle printTitle = new PrintTitle();
        PrintHeader printHeader = new PrintHeader();

        PrintWorkersList printWorkersList = new PrintWorkersList();
        PrintVacation printVacation = new PrintVacation();
        PrintSearchEngine printSearchEngine = new PrintSearchEngine();
        PrintConfigurations printConfigurations = new PrintConfigurations();

        PrintHoliday printHoliday = new PrintHoliday();
        PrintWorkerVacation printWorkerVacation = new PrintWorkerVacation();
        PrintTeamVacation printTeamVacation = new PrintTeamVacation();

        map.put("a", printTitle);
        map.put("0", printHeader);

        map.put("1", printWorkersList);
        map.put("2", printVacation);
        map.put("3", printSearchEngine);
        map.put("4", printConfigurations);

        map.put("31", printHoliday);
        map.put("32", printWorkerVacation);
        map.put("33", printTeamVacation);

        map.get("a").doAction();
        map.get("0").doAction();
        if (printMenuPage().equals("01")) {
            map.get("1").doAction();
            if(printMenuPage().equals("011")) {
                System.out.println("Pracownik dodany");
            }
        }

    }

    public String printMenuPage() {

        Scanner scanner = new Scanner(System.in);
        return this.page += scanner.nextLine();
    }

    public void returnToPreviousPage() {

        this.page = this.page.substring(0, this.page.length() - 1);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
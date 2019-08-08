package com.infoshareacademy.jjdd7.menu;

import com.infoshareacademy.jjdd7.menu.menuprint.*;

import java.util.HashMap;
import java.util.Map;

public class MenuTree {


    public Map<String, Menu> buildMenuTree() {
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

        map.put("m", printHeader);

        map.put("m1", printWorkersList);
        map.put("m2", printVacation);
        map.put("m3", printSearchEngine);
        map.put("m4", printConfigurations);

        map.put("m31", printHoliday);
        map.put("m32", printWorkerVacation);
        map.put("m33", printTeamVacation);

        return map;
    }
}
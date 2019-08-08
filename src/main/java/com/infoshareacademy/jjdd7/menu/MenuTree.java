package com.infoshareacademy.jjdd7.menu;

import com.infoshareacademy.jjdd7.menu.menufunctions.*;
import com.infoshareacademy.jjdd7.menu.menuprint.*;

import java.util.HashMap;
import java.util.Map;

public class MenuTree {

    public Map<String, Menu> buildMenuTree() {

        Map<String, Menu> map = new HashMap<>();
        /* METODY DRUKUJĄCE */
        PrintHeader printHeader = new PrintHeader();

        PrintWorkersList printWorkersList = new PrintWorkersList();
        PrintVacation printVacation = new PrintVacation();
        PrintSearchEngine printSearchEngine = new PrintSearchEngine();
        PrintConfigurations printConfigurations = new PrintConfigurations();

        PrintHoliday printHoliday = new PrintHoliday();
        PrintWorkerVacation printWorkerVacation = new PrintWorkerVacation();
        PrintTeamVacation printTeamVacation = new PrintTeamVacation();

        /* METODY FUNKCJONALNE */
        WorkerCreator workerCreator = new WorkerCreator();
        RemovingWorker removingWorker = new RemovingWorker();

        AddVacation addVacation = new AddVacation();
        CancelVacation cancelVacation = new CancelVacation();

        EnterNameHoliday enterNameHoliday = new EnterNameHoliday();
        EnterDateHoliday enterDateHoliday = new EnterDateHoliday();

        SearchVacationWorker searchVacationWorker = new SearchVacationWorker();
        SearchDateVacationWorker searchDateVacationWorker = new SearchDateVacationWorker();

        SearchVacationTeam searchVacationTeam = new SearchVacationTeam();
        SearchDateVacationTeam searchDateVacationTeam = new SearchDateVacationTeam();

        ImportSettings importSettings = new ImportSettings();
        FormatDate formatDate = new FormatDate();

        /* MAPA */
        map.put("m", printHeader);

        map.put("m1", printWorkersList);
            map.put("m11", workerCreator);
            map.put("m12", removingWorker);
        map.put("m2", printVacation);
            map.put("m21", addVacation);
            map.put("m22", cancelVacation);
        map.put("m3", printSearchEngine);
            map.put("m31", printHoliday);
                map.put("m311", enterNameHoliday);
                map.put("m312", enterDateHoliday);
            map.put("m32", printWorkerVacation);
                map.put("m321", searchVacationWorker);
                map.put("m322", searchDateVacationWorker);
            map.put("m33", printTeamVacation);
                map.put("m331", searchVacationTeam);
                map.put("m332", searchDateVacationTeam);
        map.put("m4", printConfigurations);
            map.put("m41", importSettings);
            map.put("m42", formatDate);

        return map;
    }
}
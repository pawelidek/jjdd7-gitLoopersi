package com.infoshareacademy.jjdd7.menu;

import com.infoshareacademy.jjdd7.menu.menufunctions.*;
import com.infoshareacademy.jjdd7.menu.menuprint.*;

import java.util.HashMap;
import java.util.Map;

public class MenuTree {

    public Map<String, Menu> buildMenuTree() {

        Map<String, Menu> map = new HashMap<>();

        /* METODY DRUKUJĄCE */
        HeaderPrinter headerPrinter = new HeaderPrinter();

        WorkersListPrinter workersListPrinter = new WorkersListPrinter();
        VacationPrinter vacationPrinter = new VacationPrinter();
        SearchEnginePrinter searchEnginePrinter = new SearchEnginePrinter();
        ConfigurationsPrinter configurationsPrinter = new ConfigurationsPrinter();

        HolidayPrinter holidayPrinter = new HolidayPrinter();
        WorkerVacationPrinter workerVacationPrinter = new WorkerVacationPrinter();
        TeamVacationPrinter teamVacationPrinter = new TeamVacationPrinter();

        /* METODY FUNKCJONALNE */
        WorkerCreator workerCreator = new WorkerCreator();
        WorkerRemover workerRemover = new WorkerRemover();

        VacationAdder vacationAdder = new VacationAdder();
        VacationCanceller vacationCanceller = new VacationCanceller();

        HolidayNameCapturer holidayNameCapturer = new HolidayNameCapturer();
        HolidayDateCapturer holidayDateCapturer = new HolidayDateCapturer();

        WorkerVacationSearcher workerVacationSearcher = new WorkerVacationSearcher();
        WorkerVacationDateSearcher workerVacationDateSearcher = new WorkerVacationDateSearcher();

        TeamVacationSearcher teamVacationSearcher = new TeamVacationSearcher();
        TeamVacationDateSearcher teamVacationDateSearcher = new TeamVacationDateSearcher();

        SettingsImporter settingsImporter = new SettingsImporter();
        DateFormatter dateFormatter = new DateFormatter();
        SortingChanger sortingChanger = new SortingChanger();

        /* MAPA */
        map.put("m", headerPrinter);

        map.put("m1", workersListPrinter);
        map.put("m11", workerCreator);
        map.put("m12", workerRemover);
        map.put("m2", vacationPrinter);
        map.put("m21", vacationAdder);
        map.put("m22", vacationCanceller);
        map.put("m3", searchEnginePrinter);
        map.put("m31", holidayPrinter);
        map.put("m311", holidayNameCapturer);
        map.put("m312", holidayDateCapturer);
        map.put("m32", workerVacationPrinter);
        map.put("m321", workerVacationSearcher);
        map.put("m322", workerVacationDateSearcher);
        map.put("m33", teamVacationPrinter);
        map.put("m331", teamVacationSearcher);
        map.put("m332", teamVacationDateSearcher);
        map.put("m4", configurationsPrinter);
        map.put("m41", settingsImporter);
        map.put("m42", dateFormatter);
        map.put("m43", sortingChanger);

        return map;
    }
}
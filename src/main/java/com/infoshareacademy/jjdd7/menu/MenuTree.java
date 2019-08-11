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

        EmployeesListPrinter employeesListPrinter = new EmployeesListPrinter();
        VacationPrinter vacationPrinter = new VacationPrinter();
        SearchEnginePrinter searchEnginePrinter = new SearchEnginePrinter();
        ConfigurationsPrinter configurationsPrinter = new ConfigurationsPrinter();
        TeamsListPrinter teamsListPrinter = new TeamsListPrinter();

        HolidayPrinter holidayPrinter = new HolidayPrinter();
        EmployeesVacationPrinter employeesVacationPrinter = new EmployeesVacationPrinter();
        TeamVacationPrinter teamVacationPrinter = new TeamVacationPrinter();

        /* METODY FUNKCJONALNE */
        EmployeeAdder employeeAdder = new EmployeeAdder();
        EmployeeDeleter employeeDeleter = new EmployeeDeleter();

        TeamAdder teamAdder = new TeamAdder();
        TeamNameChanger teamNameChanger = new TeamNameChanger();
        TeamDeleter teamDeleter = new TeamDeleter();

        VacationAdder vacationAdder = new VacationAdder();
        VacationCanceller vacationCanceller = new VacationCanceller();

        HolidayNameCapturer holidayNameCapturer = new HolidayNameCapturer();
        HolidayDateCapturer holidayDateCapturer = new HolidayDateCapturer();

        WorkerVacationSearcher workerVacationSearcher = new WorkerVacationSearcher();
        EmployeeVacationDateSearcher employeeVacationDateSearcher = new EmployeeVacationDateSearcher();

        TeamVacationSearcher teamVacationSearcher = new TeamVacationSearcher();
        TeamVacationDateSearcher teamVacationDateSearcher = new TeamVacationDateSearcher();

        SettingsImporter settingsImporter = new SettingsImporter();
        DateFormatter dateFormatter = new DateFormatter();
        SortingChanger sortingChanger = new SortingChanger();

        /* MAPA */
        map.put("m", headerPrinter);

        map.put("m1", employeesListPrinter);
        map.put("m11", employeeAdder);
        map.put("m12", employeeDeleter);
        map.put("m2", teamsListPrinter);
        map.put("m21", teamAdder);
        map.put("m22", teamNameChanger);
        map.put("m23", teamDeleter);
        map.put("m3", vacationPrinter);
        map.put("m31", vacationAdder);
        map.put("m32", vacationCanceller);
        map.put("m4", searchEnginePrinter);
        map.put("m41", holidayPrinter);
        map.put("m411", holidayNameCapturer);
        map.put("m412", holidayDateCapturer);
        map.put("m42", employeesVacationPrinter);
        map.put("m421", workerVacationSearcher);
        map.put("m422", employeeVacationDateSearcher);
        map.put("m43", teamVacationPrinter);
        map.put("m431", teamVacationSearcher);
        map.put("m432", teamVacationDateSearcher);
        map.put("m5", configurationsPrinter);
        map.put("m51", settingsImporter);
        map.put("m52", dateFormatter);
        map.put("m53", sortingChanger);

        return map;
    }
}
package com.infoshareacademy.gitLoopersi.menu;

import com.infoshareacademy.gitLoopersi.employee.EmployeeMapper;
import com.infoshareacademy.gitLoopersi.menu.menufunctions.*;
import com.infoshareacademy.gitLoopersi.menu.menuprint.*;
import com.infoshareacademy.gitLoopersi.search.HolidayMapper;
import com.infoshareacademy.gitLoopersi.team.TeamMapper;

import com.infoshareacademy.gitLoopersi.vacation.VacationMapper;
import java.util.HashMap;
import java.util.Map;

public class MenuTree {

  public Map<String, Menu> buildMenuTree() {

    Map<String, Menu> map = new HashMap<>();

    HeaderPrinter headerPrinter = new HeaderPrinter();

    EmployeesListPrinter employeesListPrinter = new EmployeesListPrinter();
    VacationPrinter vacationPrinter = new VacationPrinter();
    SearchEnginePrinter searchEnginePrinter = new SearchEnginePrinter();
    ConfigurationsPrinter configurationsPrinter = new ConfigurationsPrinter();
    TeamsListPrinter teamsListPrinter = new TeamsListPrinter();

    HolidayPrinter holidayPrinter = new HolidayPrinter();
    EmployeesVacationPrinter employeesVacationPrinter = new EmployeesVacationPrinter();
    TeamVacationPrinter teamVacationPrinter = new TeamVacationPrinter();

    WorkerVacationSearcher workerVacationSearcher = new WorkerVacationSearcher();
    EmployeeVacationDateSearcher employeeVacationDateSearcher = new EmployeeVacationDateSearcher();

    TeamVacationSearcher teamVacationSearcher = new TeamVacationSearcher();
    TeamVacationDateSearcher teamVacationDateSearcher = new TeamVacationDateSearcher();

    SettingsImporter settingsImporter = new SettingsImporter();
    DateFormatter dateFormatter = new DateFormatter();
    SortingChanger sortingChanger = new SortingChanger();

    TeamMapper teamMapper = new TeamMapper();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    HolidayMapper holidayMapper = new HolidayMapper();
    VacationMapper vacationMapper = new VacationMapper();

    map.put("m", headerPrinter);

    map.put("m1", employeesListPrinter);
    map.put("m11", employeeMapper::validateCorrectInputDataForNewEmployee);
    map.put("m12", employeeMapper::validateCorrectInputDataForDeleteEmployee);
    map.put("m2", teamsListPrinter);
    map.put("m21", teamMapper::validateCorrectInputDataForNewTeam);
    map.put("m22", teamMapper::validateCorrectInputDataForUpdateTeam);
    map.put("m23", teamMapper::validateCorrectInputDataForDeleteTeam);
    map.put("m3", vacationPrinter);
    map.put("m31", vacationMapper::validateDataForDefineVacation);
    map.put("m32", vacationMapper::validateCancellationOfVacation);
    map.put("m4", searchEnginePrinter);
    map.put("m41", holidayPrinter);
    map.put("m411", holidayMapper::validateCorrectInputDataForHolidayName);
    map.put("m412", holidayMapper::validateCorrectInputDataForHolidayDate);
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
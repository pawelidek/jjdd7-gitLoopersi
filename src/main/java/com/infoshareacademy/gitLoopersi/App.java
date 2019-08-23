package com.infoshareacademy.gitLoopersi;

import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitLoopersi.menu.menuprint.TitlePrinter;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class App {

  public static void main(String[] args) {

//    EmployeeService employeeService = new EmployeeService();
//    employeeService.loadEmployeeData();
//
//    EmployeeVacationSearchEngine employeeVacationSearcher = new EmployeeVacationSearchEngine();
//
//    employeeVacationSearcher.searchForEmployee();
//
//    System.out.println(employeeVacationSearcher.getSearchedEmployee());

    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.loadUserConfiguration();

    ConsoleCleaner.cleanConsole();

    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    titlePrinter.doAction();
    menuAction.doMenuAction();
  }
}
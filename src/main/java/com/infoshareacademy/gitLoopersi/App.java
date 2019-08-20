package com.infoshareacademy.gitLoopersi;

import com.infoshareacademy.gitLoopersi.employee.EmployeeService;
import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitLoopersi.menu.menuprint.TitlePrinter;
import com.infoshareacademy.gitLoopersi.vacation.VacationMapper;
import com.infoshareacademy.gitLoopersi.vacation.VacationService;

public class App {

  public static void main(String[] args) {

/*    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    System.out.print("\033[H\033[2J");
    System.out.flush();
    titlePrinter.doAction();
    menuAction.doMenuAction();*/

    EmployeeService employeeService = new EmployeeService();
    employeeService.loadEmployeeData();

    VacationService vacationService = new VacationService();
    vacationService.loadVacationData();

    VacationMapper vacationMapper = new VacationMapper();
    vacationMapper.validateDataForDefineVacation();
  }
}
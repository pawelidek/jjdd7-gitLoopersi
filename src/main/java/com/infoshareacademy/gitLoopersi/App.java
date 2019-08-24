package com.infoshareacademy.gitLoopersi;

import com.infoshareacademy.gitLoopersi.employee.EmployeeService;
import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitLoopersi.menu.menuprint.TitlePrinter;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;
import com.infoshareacademy.gitLoopersi.team.TeamService;
import com.infoshareacademy.gitLoopersi.vacation.VacationService;

public class App {

  public static void main(String[] args) {

    new EmployeeService().loadEmployeeData();
    new VacationService().loadVacationData();
    new TeamService().loadTeamData();

    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.loadUserConfiguration();

    ConsoleCleaner.cleanConsole();

    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    titlePrinter.doAction();
    menuAction.doMenuAction();
  }
}
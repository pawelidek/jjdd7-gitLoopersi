package com.infoshareacademy.gitloopersi;

import com.infoshareacademy.gitloopersi.employee.EmployeeService;
import com.infoshareacademy.gitloopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitloopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitloopersi.menu.menuprint.TitlePrinter;
import com.infoshareacademy.gitloopersi.parser.Parser;
import com.infoshareacademy.gitloopersi.properties.AppConfigMapper;
import com.infoshareacademy.gitloopersi.team.TeamService;
import com.infoshareacademy.gitloopersi.vacation.VacationService;

public class App {

  public static void main(String[] args) {

    new Parser();
    new EmployeeService().loadEmployeeData();
    new VacationService().loadVacationData();
    new TeamService().loadTeamData();

    new AppConfigMapper().loadUserConfiguration();

    ConsoleCleaner.cleanConsole();

    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    titlePrinter.doAction();
    menuAction.doMenuAction();
  }
}
package com.infoshareacademy.gitLoopersi;

import com.infoshareacademy.gitLoopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitLoopersi.menu.menuprint.TitlePrinter;
import com.infoshareacademy.gitLoopersi.vacation.VacationMapper;

public class App {

  public static void main(String[] args) {

/*    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    System.out.print("\033[H\033[2J");
    System.out.flush();
    titlePrinter.doAction();
    menuAction.doMenuAction();*/

    VacationMapper vacationMapper = new VacationMapper();
    vacationMapper.validateDataForDefineVacation();
  }
}
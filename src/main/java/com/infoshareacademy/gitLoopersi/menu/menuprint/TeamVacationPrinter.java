package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class TeamVacationPrinter implements Menu {

  @Override
  public void doAction() {

    System.out.println("Main menu >> Search engine >> Team vacation");
    System.out.println("\n1. Find Find vacation team");
    System.out.println("0. Return");
    System.out.println("\nType \"exit\" to close the app");
  }
}

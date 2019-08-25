package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class TeamVacationPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Vacation holiday engine for a given team");
    System.out.println("\n1. Find vacation team");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
  }
}

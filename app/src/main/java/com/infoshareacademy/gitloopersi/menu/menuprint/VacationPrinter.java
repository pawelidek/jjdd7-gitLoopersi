package com.infoshareacademy.gitloopersi.menu.menuprint;

import com.infoshareacademy.gitloopersi.menu.Menu;

public class VacationPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Vacation");
    System.out.println("\n1. Add vacation");
    System.out.println("2. Cancel vacation");
    System.out.println("0. Return");
    System.out.println("\nType \"exit\" to close the app");
  }
}

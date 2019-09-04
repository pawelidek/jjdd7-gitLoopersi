package com.infoshareacademy.gitloopersi.menu.menuprint;

import com.infoshareacademy.gitloopersi.menu.Menu;

public class HolidayPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Search engine >> Holiday");
    System.out.println("\n1. By name");
    System.out.println("2. By date");
    System.out.println("0. Return");
    System.out.println("\nType \"exit\" to close the app");
  }
}

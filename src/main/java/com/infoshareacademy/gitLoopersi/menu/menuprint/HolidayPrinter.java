package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class HolidayPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Search engine for holidays and non-working days.");
    System.out.println("\n1. Find holiday by name");
    System.out.println("2. Find holiday by date");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
  }
}

package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class CalendarPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("1. Show Calendar");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
  }
}

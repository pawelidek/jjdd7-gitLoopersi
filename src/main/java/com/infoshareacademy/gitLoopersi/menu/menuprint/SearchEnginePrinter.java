package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class SearchEnginePrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Search engine.");
    System.out.println("\n1. Holiday");
    System.out.println("2. Workers vacation");
    System.out.println("3. Teams vacation");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
  }
}

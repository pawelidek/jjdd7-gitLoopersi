package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class SearchEnginePrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Search engine");
    System.out.println("\n1. Holiday");
    System.out.println("2. Employee vacation");
    System.out.println("3. Team vacation");
    System.out.println("0. Return");
    System.out.println("\nType \"exit\" to close the app");
  }
}

package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class ConfigurationsPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Configuration options");
    System.out.println("\n1. Import settings");
    System.out.println("2. Change date format");
    System.out.println("3. Change sorting for workers in team (ASC/DESC)");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
  }
}
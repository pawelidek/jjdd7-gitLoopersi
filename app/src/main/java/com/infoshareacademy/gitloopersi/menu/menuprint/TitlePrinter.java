package com.infoshareacademy.gitloopersi.menu.menuprint;

import com.infoshareacademy.gitloopersi.menu.Menu;

public class TitlePrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Welcome to Employee Vacation Calendar!\n");
  }
}
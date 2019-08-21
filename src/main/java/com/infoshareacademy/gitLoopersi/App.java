package com.infoshareacademy.gitLoopersi;

import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitLoopersi.menu.menuprint.TitlePrinter;

public class App {

  public static void main(String[] args) {

    ConsoleCleaner.cleanConsole();

    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    titlePrinter.doAction();
    menuAction.doMenuAction();
  }
}
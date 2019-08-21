package com.infoshareacademy.gitLoopersi;

import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.menu.menuaction.MenuAction;
import com.infoshareacademy.gitLoopersi.menu.menuprint.TitlePrinter;
import com.infoshareacademy.gitLoopersi.properties.AppConfigLoader;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class App {

  public static void main(String[] args) {
    AppConfigLoader appConfigLoader = new AppConfigLoader();
    appConfigLoader.loadConfig();
    ConsoleCleaner.cleanConsole();

    MenuAction menuAction = new MenuAction();
    TitlePrinter titlePrinter = new TitlePrinter();

    titlePrinter.doAction();
    menuAction.doMenuAction();
  }
}
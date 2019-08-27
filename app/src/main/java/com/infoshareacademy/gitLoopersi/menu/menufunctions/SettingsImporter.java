package com.infoshareacademy.gitLoopersi.menu.menufunctions;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class SettingsImporter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Configuration >> Import settings");

    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.loadUserConfiguration();

    System.out.println("\nImporting settings has been finished successfully.");
    System.out.println("\nType '0' to return");
    System.out.println("\nType \"exit\" to close the app");
  }
}

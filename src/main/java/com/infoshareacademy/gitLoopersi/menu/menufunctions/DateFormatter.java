package com.infoshareacademy.gitLoopersi.menu.menufunctions;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class DateFormatter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Configuration >> Date formatter");

    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.validateCorrectInputDataForConfigurationOfDateFormatting();

    System.out.println("\nType '0' to return.");
    System.out.println("\nType \"exit\" to close the app");
  }
}

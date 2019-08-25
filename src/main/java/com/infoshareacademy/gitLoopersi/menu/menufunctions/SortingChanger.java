package com.infoshareacademy.gitLoopersi.menu.menufunctions;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class SortingChanger implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Configuration >> Sorting changer");

    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.validateCorrectInputDataForConfigurationOfSorting();
    System.out.println("\nType '0' to return");
    System.out.println("\nType \"exit\" to close the app");
  }
}
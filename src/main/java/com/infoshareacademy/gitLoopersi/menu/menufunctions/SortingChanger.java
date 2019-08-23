package com.infoshareacademy.gitLoopersi.menu.menufunctions;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class SortingChanger implements Menu {

  @Override
  public void doAction() {
    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.validateCorrectInputDataForConfigurationOfSorting();
    System.out.println("Type '0' to return.");
  }
}
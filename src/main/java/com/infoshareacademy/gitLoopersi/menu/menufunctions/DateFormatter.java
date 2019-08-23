package com.infoshareacademy.gitLoopersi.menu.menufunctions;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class DateFormatter implements Menu {

  @Override
  public void doAction() {
    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.validateCorrectInputDataForConfigurationOfDateFormatting();
    System.out.println("Type '0' to return.");
  }
}

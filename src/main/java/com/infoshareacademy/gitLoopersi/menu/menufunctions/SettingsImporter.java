package com.infoshareacademy.gitLoopersi.menu.menufunctions;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfigMapper;

public class SettingsImporter implements Menu {

  @Override
  public void doAction() {
    AppConfigMapper appConfigMapper = new AppConfigMapper();
    appConfigMapper.loadUserConfiguration();
    System.out.println("Importing settings has been finished successfully.");
    System.out.println("Type '0' to return.");
  }
}

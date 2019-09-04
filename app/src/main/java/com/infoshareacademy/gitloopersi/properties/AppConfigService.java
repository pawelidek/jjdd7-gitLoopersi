package com.infoshareacademy.gitloopersi.properties;

import com.infoshareacademy.gitloopersi.deserialization.AppConfigLoader;
import com.infoshareacademy.gitloopersi.serialization.AppConfigSaver;

public class AppConfigService {

  public void importSettings() {
    AppConfigLoader appConfigLoader = new AppConfigLoader();
    appConfigLoader.loadConfig();
  }

  public void setConfigurationOfSorting(String newSortingType) {
    AppConfig.setSortType(newSortingType);
    saveConfiguration();
  }

  public void setConfigurationOfDateFormatting(String newDateFormat) {
    AppConfig.setDateFormat(newDateFormat);
    saveConfiguration();
  }

  private void saveConfiguration() {
    AppConfigSaver appConfigSaver = new AppConfigSaver();
    appConfigSaver.saveProperty();
    System.out.println("\nProcedure of changing configuration finished successfully.");
  }
}

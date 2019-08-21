package com.infoshareacademy.gitLoopersi.properties;

public class AppConfigService {

  public void importSettings(){
    AppConfigLoader appConfigLoader = new AppConfigLoader();
    appConfigLoader.loadConfig();
  }

  public void setConfigurationOfSorting(String newSortingType){
    AppConfig.setSortType(newSortingType);
    saveConfiguration();
  }

  public void setConfigurationOfDateFormatting(String newDateFormat){
    AppConfig.setDateFormat(newDateFormat);
    saveConfiguration();
  }

  private void saveConfiguration(){
    AppConfigSaver appConfigSaver = new AppConfigSaver();
    appConfigSaver.saveProperty();
    System.out.println("\nProcedure of changing configuration finished successfully.");
    System.out.println("\nType '0' to return or 'Enter' to add another employee.");
  }
}

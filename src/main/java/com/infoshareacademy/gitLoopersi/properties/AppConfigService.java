package com.infoshareacademy.gitLoopersi.properties;

public class AppConfigService {

  public void importSettings(){
    AppConfigLoader appConfigLoader = new AppConfigLoader();
    appConfigLoader.loadConfig();
  }

  public void setConfigurationOfSorting(String newSorting){
    AppConfig.sortType=newSorting;
    saveConfiguration();
  }

  public void setConfigurationOfDateFormatting(String newDateFormat){
    AppConfig.dateFormat=newDateFormat;
    saveConfiguration();
  }

  private void saveConfiguration(){
    AppConfigSaver appConfigSaver = new AppConfigSaver();
    appConfigSaver.saveProperty();
  }
}

package com.infoshareacademy.gitLoopersi.properties;

public class AppConfigMapper {
  public void loadUserConfiguration(){
    AppConfigService appConfigService = new AppConfigService();
    appConfigService.importSettings();
  }


}

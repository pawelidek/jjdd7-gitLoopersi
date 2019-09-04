package com.infoshareacademy.gitloopersi.properties;

public class AppConfig {

  private static String dateFormat;
  private static String sortType;

  private AppConfig() {
  }

  public static String getDateFormat() {
    return dateFormat;
  }

  public static String getSort() {
    return sortType;
  }

  public static void setDateFormat(String dateFormat) {
    AppConfig.dateFormat = dateFormat;
  }

  public static void setSortType(String sortType) {
    AppConfig.sortType = sortType;
  }
}

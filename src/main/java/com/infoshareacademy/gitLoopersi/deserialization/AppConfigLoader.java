package com.infoshareacademy.gitLoopersi.deserialization;

import com.infoshareacademy.gitLoopersi.properties.AppConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Properties;

public class AppConfigLoader {

  private final String FILEPATH = "application.properties";

  public void loadConfig() {
    try (InputStream input = new FileInputStream(FILEPATH)) {
      Properties appProperties = new Properties();
      appProperties.load(input);
      AppConfig.setDateFormat(loadDataFormatChecker(appProperties));
      AppConfig.setSortType(loadSortTypeChecker(appProperties));
    } catch (IOException iOE) {
      System.out.println("Error with loading properties");
    }
  }

  private String loadDataFormatChecker(Properties properties) {
    String validatedData = null;
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
          properties.getProperty("date.format"));
      validatedData = Optional.ofNullable(properties.getProperty("date.format"))
          .orElse("yyyy.MM.dd");
    } catch (IllegalArgumentException iAE) {
      System.out
          .println("Bad date format in properties file, default value \"yyyy.MM.dd\" has been set");
      validatedData = "yyyy.MM.dd";
    } finally {
      return validatedData;
    }
  }

  private String loadSortTypeChecker(Properties properties) {
    String validatedSortType = null;
    try {
      if (properties.getProperty("sort.type").equals("ASC") || properties.getProperty("sort.type")
          .equals("DESC")) {
        validatedSortType = properties.getProperty("employee.sort");
      } else {
        System.out.println(
            "Bad sort or no type format in properties file, default value \"ASC\" has been set");
        validatedSortType = "ASC";
      }
    } catch (NullPointerException nPE) {
      System.out.println(
          "Bad sort or no type format in properties file, default value \"ASC\" has been set");
      validatedSortType = "ASC";
    }
    return validatedSortType;
  }
}

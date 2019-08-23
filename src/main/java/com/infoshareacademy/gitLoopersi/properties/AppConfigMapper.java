package com.infoshareacademy.gitLoopersi.properties;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppConfigMapper {

  public void loadUserConfiguration() {
    AppConfigService appConfigService = new AppConfigService();
    appConfigService.importSettings();
  }

  public void validateCorrectInputDataForConfigurationOfDateFormatting() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("The process of date formatting new configuration.\n");
    System.out.print("Enter new date format: ");
    String dateFormat = null;
    do {
      String tempDateFormat = scanner.nextLine();
      try {
        DateTimeFormatter formatChecker = DateTimeFormatter.ofPattern(tempDateFormat);
        dateFormat = tempDateFormat;
      } finally {
        if (dateFormat == null) {
          System.out.println("Wrong date format! Please enter date format correctly.");
        }
      }
    } while (dateFormat == null);
    AppConfigService appConfigService = new AppConfigService();
    appConfigService.setConfigurationOfDateFormatting(dateFormat);
  }

  public void validateCorrectInputDataForConfigurationOfSorting() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("The process of sorting property new configuration.\n");
    System.out.print("Enter new sorting configuration (ASC/DESC): ");
    String sortType = null;
    do {
      String tempSortType = scanner.nextLine();
      if (tempSortType.toUpperCase().equals("ASC") || tempSortType.toUpperCase().equals("DESC")) {
        sortType = tempSortType;
      }
    } while (sortType == null);
    AppConfigService appConfigService = new AppConfigService();
    appConfigService.setConfigurationOfSorting(sortType);
  }
}

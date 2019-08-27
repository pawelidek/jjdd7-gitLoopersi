package com.infoshareacademy.gitLoopersi.holiday;

import java.time.LocalDate;

public class HolidayService {

  private static final String fileName = "HolidaysApi.json";

  void searchHolidayByName(LocalDate date, String name) {
    System.out.println(date + " " + name);
  }

  public static String getFileName() {
    return fileName;
  }
}
package com.infoshareacademy.gitloopersi.repository;

import com.infoshareacademy.gitloopersi.domain.Holiday;
import java.util.ArrayList;
import java.util.List;

public class HolidayRepository {

  private static List<Holiday> allHolidays = new ArrayList<>();

  private HolidayRepository() {
  }

  public static List<Holiday> getAllHolidays() {
    return allHolidays;
  }

  public static void setAllHolidays(List<Holiday> allHolidays) {
    HolidayRepository.allHolidays = allHolidays;
  }
}
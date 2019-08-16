package com.infoshareacademy.jjdd7.repository;

import com.infoshareacademy.jjdd7.domain.Holiday;

import java.util.List;

public class HolidayRepository {
    private static List<Holiday> allHolidays;
    private static final String fileName = "HolidaysApi.json";

    public static List<Holiday> getAllHolidays() {
        return allHolidays;
    }

    public static void setAllHolidays(List<Holiday> allHolidays) {
        HolidayRepository.allHolidays = allHolidays;
    }

    public static String getFileName() {
        return fileName;
    }
}

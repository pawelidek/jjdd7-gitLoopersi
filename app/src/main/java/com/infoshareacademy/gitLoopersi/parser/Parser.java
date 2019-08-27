package com.infoshareacademy.gitLoopersi.parser;

import com.infoshareacademy.gitLoopersi.domain.Holiday;
import com.infoshareacademy.gitLoopersi.holiday.HolidayService;
import com.infoshareacademy.gitLoopersi.repository.HolidayRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

  public Parser() {
    loadDateToArray();
  }

  private String createTextJSON() {
    StringBuilder stringBuilder = new StringBuilder();
    List<String> tempLinesList = null;

    try {
      tempLinesList = Files.readAllLines(Paths.get(HolidayService.getFileName()));
    } catch (IOException e) {
      System.out.println("Problem with IO occurred");
    }
    for (String elem : tempLinesList) {
      stringBuilder.append(elem);
    }
    return stringBuilder.toString();
  }

  private JSONObject createJSONObject() {
    return new JSONObject(createTextJSON());
  }

  private void loadDateToArray() {
    JSONArray arrayOfHolidays = createJSONObject().getJSONObject("response")
        .getJSONArray("holidays");
    List<Holiday> listOfHolidays = new ArrayList<>();
    for (int i = 0; i < arrayOfHolidays.length(); i++) {
      String name = arrayOfHolidays.getJSONObject(i).getString("name");
      LocalDate date = null;
      try {
        date = new SimpleDateFormat("yyyy-MM-dd")
            .parse(arrayOfHolidays.getJSONObject(i).getJSONObject("date").getString("iso"))
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
      } catch (java.text.ParseException pe) {
        System.out.println("Problem with parsing data occured");
      }
      String description;
      try {
        description = arrayOfHolidays.getJSONObject(i).getString("description");
      } catch (org.json.JSONException JSONEx) {
        description = "null";
      }
      String tempTypeOfHoliday = arrayOfHolidays.getJSONObject(i).getJSONArray("type").getString(0);
      TypeOfHoliday typeOfHoliday = null;
      switch (tempTypeOfHoliday) {
        case "Observance":
          typeOfHoliday = TypeOfHoliday.OBSERVANCE;
          break;
        case "National holiday":
          typeOfHoliday = TypeOfHoliday.NATIONAL_HOLIDAY;
          break;
        case "Season":
          typeOfHoliday = TypeOfHoliday.SEASON;
          break;
        default:
          System.out.println("Problem with Types occured: there is no such type of holiday");
      }
      listOfHolidays.add(new Holiday(name, date, typeOfHoliday, description));
    }
    HolidayRepository.setAllHolidays(listOfHolidays);
  }
}
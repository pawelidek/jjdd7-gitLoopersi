package com.infoshareacademy.gitLoopersi.domain.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infoshareacademy.gitLoopersi.parser.HolidayType;
import java.time.LocalDate;

public class Holiday {

  @JsonProperty("name")
  private String name;
  @JsonProperty("date")
  private LocalDate date;
  @JsonProperty("type")
  private HolidayType holidayType;
  @JsonProperty("description")
  private String description;

  public Holiday() {
  }

  public Holiday(String name, LocalDate date, HolidayType holidayType,
      String description) {
    this.name = name;
    this.date = date;
    this.holidayType = holidayType;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDate() {
    return date;
  }

  public HolidayType getHolidayType() {
    return holidayType;
  }

  @Override
  public String toString() {
    return "Holiday{" +
        "name='" + name + '\'' +
        ", date=" + date +
        ", typeOfHoliday=" + holidayType +
        ", description='" + description + '\'' +
        '}';
  }
}
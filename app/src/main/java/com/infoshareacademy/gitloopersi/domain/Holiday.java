package com.infoshareacademy.gitloopersi.domain;

import com.infoshareacademy.gitloopersi.parser.TypeOfHoliday;
import java.time.LocalDate;


public class Holiday {

  private String name;
  private LocalDate date;
  private TypeOfHoliday typeOfHoliday;
  private String description;

  public Holiday() {
  }

  public Holiday(String name, LocalDate date, TypeOfHoliday typeOfHoliday, String description) {
    this.name = name;
    this.date = date;
    this.typeOfHoliday = typeOfHoliday;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDate() {
    return date;
  }

  public TypeOfHoliday getTypeOfHoliday() {
    return typeOfHoliday;
  }

  @Override
  public String toString() {
    return "Holiday{" +
        "name='" + name + '\'' +
        ", date=" + date +
        ", type=" + typeOfHoliday +
        ", description='" + description + '\'' +
        '}';
  }
}

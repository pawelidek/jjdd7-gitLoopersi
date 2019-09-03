package com.infoshareacademy.gitLoopersi.types;

public enum HolidayType {
  NATIONAL_HOLIDAY("NATIONAL HOLIDAY"), OBSERVANCE("OBSERVANCE"), SEASON("SEASON");

  private String type;

  HolidayType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

}
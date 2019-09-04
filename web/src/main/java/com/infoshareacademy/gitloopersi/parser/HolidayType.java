package com.infoshareacademy.gitloopersi.parser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum HolidayType {
  NATIONAL_HOLIDAY("NATIONAL HOLIDAY"),
  OBSERVANCE("OBSERVANCE"),
  SEASON("SEASON");

  private String type;

  HolidayType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  @JsonCreator
  public static HolidayType deserialize(@JsonProperty("type") String type) {
    for (int i = 0; i < HolidayType.values().length; i++) {
      if (HolidayType.values()[i].getType().equals(type)) {
        return HolidayType.values()[i];
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return type;
  }
}

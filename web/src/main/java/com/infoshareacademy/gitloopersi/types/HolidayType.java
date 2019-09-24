package com.infoshareacademy.gitloopersi.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum HolidayType {
  @JsonProperty("National holiday") NATIONAL_HOLIDAY("National holiday"),
  @JsonProperty("Observance") OBSERVANCE("Observance"),
  @JsonProperty("Season") SEASON("Season");

  private String type;

  HolidayType(String type) {
    this.type = type;
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

  public String getType() {
    return this.type;
  }

  @Override
  public String toString() {
    return "HolidayType{" +
        "type='" + type + '\'' +
        '}';
  }
}
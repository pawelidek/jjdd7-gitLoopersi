package parser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeOfHoliday {
  NATIONAL_HOLIDAY("NATIONAL HOLIDAY"), OBSERVANCE("OBSERVANCE"), SEASON("SEASON");

  private String type;

  TypeOfHoliday(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  @JsonCreator
  public static TypeOfHoliday deserialize(@JsonProperty("type") String type) {
    for (int i = 0; i < TypeOfHoliday.values().length; i++) {
      if (TypeOfHoliday.values()[i].getType().equals(type)) {
        return TypeOfHoliday.values()[i];
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return type;
  }
}

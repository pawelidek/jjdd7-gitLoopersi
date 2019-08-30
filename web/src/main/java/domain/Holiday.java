package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import parser.TypeOfHoliday;

public class Holiday {

  @JsonProperty("name")
  private String name;
  @JsonProperty("date")
  private LocalDate date;
  @JsonProperty("type")
  private TypeOfHoliday typeOfHoliday;
  @JsonProperty("description")
  private String description;

  public Holiday() {
  }

  public Holiday(String name, LocalDate date, TypeOfHoliday typeOfHoliday,
      String description) {
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
        ", typeOfHoliday=" + typeOfHoliday +
        ", description='" + description + '\'' +
        '}';
  }
}
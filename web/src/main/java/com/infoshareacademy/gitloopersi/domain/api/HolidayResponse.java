package com.infoshareacademy.gitloopersi.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"locations", "states"})

public class HolidayResponse {

  @JsonProperty("name")
  private String name;
  @JsonProperty("date")
  private Date date;
  @JsonProperty("type")
  private List<HolidayType> holidayType = new ArrayList<>();
  @JsonProperty("description")
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<HolidayType> getHolidayType() {
    return holidayType;
  }

  public void setHolidayType(
      List<HolidayType> holidayType) {
    this.holidayType = holidayType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "HolidayResponse{" +
        "name='" + name + '\'' +
        ", date=" + date +
        ", holidayType=" + holidayType +
        ", description='" + description + '\'' +
        '}';
  }
}
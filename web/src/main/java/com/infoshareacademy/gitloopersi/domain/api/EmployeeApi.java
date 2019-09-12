package com.infoshareacademy.gitloopersi.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.infoshareacademy.gitloopersi.domain.entity.Team;

@JsonIgnoreProperties({"locations", "states"})

public class EmployeeApi {

  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("secondName")
  private String secondName;
  @JsonProperty("team")
  private Team team;
  @JsonProperty("startDate")
  private DateApi startDate;
  @JsonProperty("startHireDate")
  private DateApi startHireDate;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public DateApi getStartDate() {
    return startDate;
  }

  public void setStartDate(DateApi startDate) {
    this.startDate = startDate;
  }

  public DateApi getStartHireDate() {
    return startHireDate;
  }

  public void setStartHireDate(DateApi startHireDate) {
    this.startHireDate = startHireDate;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "firstName='" + firstName + '\'' +
        "secondName='" + secondName +
        ", team=" + team +
        ", startDate=" + startDate +
        ", startHireDate='" + startHireDate + '\'' +
        '}';
  }
}
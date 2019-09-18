package com.infoshareacademy.gitloopersi.domain.view;

import java.time.LocalDate;

public class EmployeeView {

  private Long id;
  private String firstName;
  private String secondName;
  private String email;
  private TeamView team;
  private LocalDate startDate;
  private LocalDate startHireDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public TeamView getTeam() {
    return team;
  }

  public void setTeam(TeamView team) {
    this.team = team;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getStartHireDate() {
    return startHireDate;
  }

  public void setStartHireDate(LocalDate startHireDate) {
    this.startHireDate = startHireDate;
  }
}

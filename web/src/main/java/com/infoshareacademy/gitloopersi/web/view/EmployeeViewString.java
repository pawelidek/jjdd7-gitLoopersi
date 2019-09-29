package com.infoshareacademy.gitloopersi.web.view;

public class EmployeeViewString {

  private Long id;
  private String firstName;
  private String secondName;
  private String email;
  private TeamView team;
  private String startDate;
  private String startHireDate;
  private boolean admin;

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

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getStartHireDate() {
    return startHireDate;
  }

  public void setStartHireDate(String startHireDate) {
    this.startHireDate = startHireDate;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}

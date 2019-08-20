package com.infoshareacademy.gitLoopersi.domain;

import java.util.Objects;

public class Vacation {

  private Long id;
  private String email;
  private String vacationDateFrom;
  private String vacationDateTo;

  public Vacation() {
  }

  public Vacation(Long id, String email, String vacationDateFrom, String vacationDateTo) {
    this.id = id;
    this.email = email;
    this.vacationDateFrom = vacationDateFrom;
    this.vacationDateTo = vacationDateTo;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getVacationDateFrom() {
    return vacationDateFrom;
  }

  public String getVacationDateTo() {
    return vacationDateTo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Vacation)) {
      return false;
    }
    Vacation vacation = (Vacation) o;
    return Objects.equals(id, vacation.id) &&
        Objects.equals(email, vacation.email) &&
        Objects.equals(vacationDateFrom, vacation.vacationDateFrom) &&
        Objects.equals(vacationDateTo, vacation.vacationDateTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, vacationDateFrom, vacationDateTo);
  }

  @Override
  public String toString() {
    return "Vacation{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", vacationDateFrom='" + vacationDateFrom + '\'' +
        ", vacationDateTo='" + vacationDateTo + '\'' +
        '}';
  }
}

package com.infoshareacademy.gitLoopersi.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Vacation {

  private Long id;
  private String email;
  private LocalDate vacationDateFrom;
  private LocalDate vacationDateTo;
  private Integer numberOfVacationDays;

  public Vacation() {
  }

  public Vacation(Integer numberOfVacationDays) {
    this.numberOfVacationDays = numberOfVacationDays;
  }

  public Vacation(Long id, String email, LocalDate vacationDateFrom, LocalDate vacationDateTo,
      Integer numberOfVacationDays) {
    this.id = id;
    this.email = email;
    this.vacationDateFrom = vacationDateFrom;
    this.vacationDateTo = vacationDateTo;
    this.numberOfVacationDays = numberOfVacationDays;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getVacationDateFrom() {
    return vacationDateFrom;
  }

  public LocalDate getVacationDateTo() {
    return vacationDateTo;
  }

  public Integer getNumberOfVacationDays() {
    return numberOfVacationDays;
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
        Objects.equals(vacationDateTo, vacation.vacationDateTo) &&
        Objects.equals(numberOfVacationDays, vacation.numberOfVacationDays);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, vacationDateFrom, vacationDateTo, numberOfVacationDays);
  }

  @Override
  public String toString() {
    return "Vacation{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", vacationDateFrom='" + vacationDateFrom + '\'' +
        ", vacationDateTo='" + vacationDateTo + '\'' +
        ", numberOfVacationDays=" + numberOfVacationDays +
        '}';
  }
}

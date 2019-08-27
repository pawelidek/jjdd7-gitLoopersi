package com.infoshareacademy.gitLoopersi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Vacation implements Serializable {

  private Long employeeId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private Integer countOfDays;

  public Vacation(Integer countOfDays) {
    this.countOfDays = countOfDays;
  }

  public Vacation(Long employeeId, LocalDate dateFrom, LocalDate dateTo,
      Integer countOfDays) {
    this.employeeId = employeeId;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.countOfDays = countOfDays;
  }

  public Vacation() {
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public LocalDate getDateTo() {
    return dateTo;
  }

  public Integer getCountOfDays() {
    return countOfDays;
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
    return Objects.equals(employeeId, vacation.employeeId) &&
        Objects.equals(dateFrom, vacation.dateFrom) &&
        Objects.equals(dateTo, vacation.dateTo) &&
        Objects.equals(countOfDays, vacation.countOfDays);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employeeId, dateFrom, dateTo, countOfDays);
  }

  @Override
  public String toString() {
    return "Vacation{" +
        "employeeId=" + employeeId +
        ", vacationDateFrom='" + dateFrom + '\'' +
        ", vacationDateTo='" + dateTo + '\'' +
        ", numberOfVacationDays=" + countOfDays +
        '}';
  }
}

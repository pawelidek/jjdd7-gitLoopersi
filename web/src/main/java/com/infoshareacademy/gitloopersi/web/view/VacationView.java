package com.infoshareacademy.gitloopersi.web.view;

import com.infoshareacademy.gitloopersi.types.StatusType;
import com.infoshareacademy.gitloopersi.types.VacationType;
import java.time.LocalDate;

public class VacationView {

  private Long id;
  private EmployeeView employee;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private Integer daysCount;
  private VacationType vacationType;
  private StatusType statusType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EmployeeView getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeView employee) {
    this.employee = employee;
  }

  public LocalDate getDateFrom() {
    return dateFrom;
  }

  public void setDateFrom(LocalDate dateFrom) {
    this.dateFrom = dateFrom;
  }

  public LocalDate getDateTo() {
    return dateTo;
  }

  public void setDateTo(LocalDate dateTo) {
    this.dateTo = dateTo;
  }

  public Integer getDaysCount() {
    return daysCount;
  }

  public void setDaysCount(Integer daysCount) {
    this.daysCount = daysCount;
  }

  public VacationType getVacationType() {
    return vacationType;
  }

  public void setVacationType(VacationType vacationType) {
    this.vacationType = vacationType;
  }

  public StatusType getStatusType() {
    return statusType;
  }

  public void setStatusType(StatusType statusType) {
    this.statusType = statusType;
  }
}

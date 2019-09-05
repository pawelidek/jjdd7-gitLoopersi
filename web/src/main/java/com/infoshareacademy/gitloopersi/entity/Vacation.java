package com.infoshareacademy.gitloopersi.entity;

import com.infoshareacademy.gitloopersi.types.StatusType;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="vacation")
public class Vacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @Column(name = "date_from")
  private LocalDate dateFrom;

  @Column(name = "date_to")
  private LocalDate dateTo;

  @Column(name = "days_count")
  private Integer daysCount;

  @Column(name = "status_type")
  private StatusType statusType;

  public Vacation() {
  }

  public Vacation(Employee employee, LocalDate dateFrom, LocalDate dateTo, Integer daysCount,
      StatusType statusType) {
    this.employee = employee;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.daysCount = daysCount;
    this.statusType = statusType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
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

  public StatusType getStatusType() {
    return statusType;
  }

  public void setStatusType(StatusType statusType) {
    this.statusType = statusType;
  }

  @Override
  public String toString() {
    return "Vacation{" +
        "id=" + id +
        ", employee=" + employee +
        ", dateFrom=" + dateFrom +
        ", dateTo=" + dateTo +
        ", daysCount=" + daysCount +
        ", statusType=" + statusType +
        '}';
  }
}

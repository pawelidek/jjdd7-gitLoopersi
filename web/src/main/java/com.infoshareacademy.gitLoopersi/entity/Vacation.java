package com.infoshareacademy.gitLoopersi.entity;

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
  @Column(name="id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="employee_id")
  private Employee employee;

  @Column(name="date_from")
  private LocalDate dateFrom;

  @Column(name="date_to")
  private LocalDate dateTo;

  @Column(name="count_of_days")
  private Integer countOfDays;

  public Vacation() {
  }

  public Vacation(Integer countOfDays) {
    this.countOfDays = countOfDays;
  }

  public Vacation(Employee employee, LocalDate dateFrom, LocalDate dateTo,
      Integer countOfDays) {
    this.employee = employee;
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.countOfDays = countOfDays;
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

  public Integer getCountOfDays() {
    return countOfDays;
  }

  public void setCountOfDays(Integer countOfDays) {
    this.countOfDays = countOfDays;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Vacation{" +
        "id=" + id +
        ", employee=" + employee +
        ", dateFrom=" + dateFrom +
        ", dateTo=" + dateTo +
        ", countOfDays=" + countOfDays +
        '}';
  }
}

package com.infoshareacademy.gitLoopersi.entity;

import com.infoshareacademy.gitLoopersi.types.HolidayType;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="holiday")
public class Holiday {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="name")
  private String name;

  @Column(name="holiday_date")
  private LocalDate date;

  @Column(name="holiday_type")
  private HolidayType holidayType;

  @Column(name="description")
  private String description;

  public Holiday() {
  }

  public Holiday(String name, LocalDate date, HolidayType holidayType,
      String description) {
    this.name = name;
    this.date = date;
    this.holidayType = holidayType;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public HolidayType getTypeOfHoliday() {
    return holidayType;
  }

  public void setTypeOfHoliday(HolidayType holidayType) {
    this.holidayType = holidayType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Holiday{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", date=" + date +
        ", holidayType="  + holidayType +
        ", description='" + description + '\'' +
        '}';
  }
}

package com.infoshareacademy.gitLoopersi.entity;

import com.infoshareacademy.gitLoopersi.parser.TypeOfHoliday;
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

  @Column(name="date_of_holiday")
  private LocalDate date;

  @Column(name="type_of_holiday")
  private TypeOfHoliday typeOfHoliday;

  @Column(name="description")
  private String description;

  public Holiday() {
  }

  public Holiday(String name, LocalDate date, TypeOfHoliday typeOfHoliday,
      String description) {
    this.name = name;
    this.date = date;
    this.typeOfHoliday = typeOfHoliday;
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

  public TypeOfHoliday getTypeOfHoliday() {
    return typeOfHoliday;
  }

  public void setTypeOfHoliday(TypeOfHoliday typeOfHoliday) {
    this.typeOfHoliday = typeOfHoliday;
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
        ", typeOfHoliday="  +
        ", description='" + description + '\'' +
        '}';
  }
}

package com.infoshareacademy.gitloopersi.domain.entity;

import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
        name = "Holiday.findAll",
        query = "SELECT h FROM Holiday h"
    ),
    @NamedQuery(
        name = "Holiday.findHolidaysInRange",
        query = "SELECT h FROM Holiday h WHERE h.date BETWEEN :dateStart AND :dateEnd"
    ),
    @NamedQuery(
        name = "Holiday.findHolidaysByPattern",
        query = "SELECT h FROM Holiday h WHERE lower(h.name) LIKE CONCAT(:pattern,'%')"
    ),
    @NamedQuery(
        name = "Holiday.findHolidayByName",
        query = "SELECT h FROM Holiday h WHERE h.name=:name"
    )
})
@Entity
@Table(name = "holiday", indexes = {
    @Index(columnList = "name", name = "name_hidx"),
    @Index(columnList = "holiday_date", name = "holiday_date_hidx")
})
public class Holiday {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;


  @Column(name = "name")
  private String name;

  @Column(name = "holiday_date")
  private LocalDate date;

  @Column(name = "holiday_type")
  private HolidayType holidayType;

  @Column(name = "description")
  private String description;

  @Column(name = "custom")
  private Boolean isCustom = false;

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

  public Boolean getCustom() {
    return isCustom;
  }

  public void setCustom(Boolean custom) {
    isCustom = custom;
  }

  public HolidayType getHolidayType() {
    return holidayType;
  }

  public void setHolidayType(HolidayType holidayType) {
    this.holidayType = holidayType;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "HolidayResponse{" +
        "name='" + name + '\'' +
        ", date=" + date +
        ", holidayType=" + holidayType +
        ", description='" + description + '\'' +
        ", isCustom=" + isCustom +
        '}';
  }
}
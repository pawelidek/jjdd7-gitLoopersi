package com.infoshareacademy.gitloopersi.domain.entity;

import com.infoshareacademy.gitloopersi.types.HolidayType;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
@NamedQueries({
    @NamedQuery(
        name = "Holiday.findAll",
        query = "SELECT h FROM Holiday h"
    )
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

  public void setName(String name) {
    this.name = name;
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
    return "Holiday{" +
        "name='" + name + '\'' +
        ", date=" + date +
        ", holidayType=" + holidayType +
        ", description='" + description + '\'' +
        ", isCustom=" + isCustom +
        '}';
  }
}

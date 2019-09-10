package com.infoshareacademy.gitloopersi.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

//  @OneToMany(mappedBy = "team")
//  private List<Employee> teamEmployees = new ArrayList<>();

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

//  public List<Employee> getTeamEmployees() {
//    return teamEmployees;
//  }
//
//  public void setTeamEmployees(
//      List<Employee> teamEmployees) {
//    this.teamEmployees = teamEmployees;
//  }
}
package com.infoshareacademy.gitloopersi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="team")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Column(name="name")
  private String name;

  @OneToMany(mappedBy = "team")
  private List<Employee> teamEmpolyees;

  public Team() {
  }

  public Team(String name) {
    this.name = name;
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

  public List<Employee> getTeamEmpolyees() {
    return teamEmpolyees;
  }

  public void setTeamEmpolyees(List<Employee> teamEmpolyees) {
    this.teamEmpolyees = teamEmpolyees;
  }

  @Override
  public String toString() {
    return "Team{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", teamEmpolyees=" + teamEmpolyees +
            '}';
  }
}
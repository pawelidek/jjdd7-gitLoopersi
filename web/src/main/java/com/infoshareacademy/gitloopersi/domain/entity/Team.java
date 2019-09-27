package com.infoshareacademy.gitloopersi.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@NamedQueries({
    @NamedQuery(
        name = "Team.findAll",
        query = "SELECT t FROM Team t"
    ),
    @NamedQuery(
        name = "Team.findEmployeeCountInTeam",
        query = "SELECT count(e) FROM Employee e LEFT JOIN Team t ON (t.id=e.team.id) WHERE t.id=:id"
    ),
    @NamedQuery(
        name = "Team.findTeamByName",
        query = "SELECT t FROM Team t WHERE t.name LIKE :name"
    )
})
@Entity
@Table(name = "team")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", unique = true)
  @NotBlank(message = "Team name cannot be empty or whitespace only!")
  private String name;

  @OneToMany(mappedBy = "team")
  private List<Employee> teamEmployees = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "team_vacation_id", unique = true)
  TeamVacationStat teamVacationStat;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Employee> getTeamEmployees() {
    return teamEmployees;
  }

  public void setTeamEmployees(
      List<Employee> teamEmployees) {
    this.teamEmployees = teamEmployees;
  }

  public TeamVacationStat getTeamVacationStat() {
    return teamVacationStat;
  }

  public void setTeamVacationStat(
      TeamVacationStat teamVacationStat) {
    this.teamVacationStat = teamVacationStat;
  }
}
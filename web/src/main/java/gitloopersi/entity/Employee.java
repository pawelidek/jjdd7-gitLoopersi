package com.infoshareacademy.gitloopersi.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="employee")
public class Employee implements Comparable<Employee> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;

  @Column(name="first_name")
  private String firstName;

  @Column(name="second_name")
  private String secondName;

  @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST})
  @JoinColumn(name="team_id")
  private Team team;

  @Column(name="start_date")
  private LocalDate startDate;

  @Column(name="start_hire_date")
  private LocalDate startHireDate;

  @OneToMany(mappedBy = "employee",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private List<Vacation> employeeVacations;


  public Employee() {
  }

  public Employee(String first_name, String secondName, Team team,
      LocalDate startDate,
      LocalDate startHireDate) {
    this.firstName = first_name;
    this.secondName = secondName;
    this.team = team;
    this.startDate = startDate;
    this.startHireDate = startHireDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String first_name) {
    this.firstName = first_name;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public Team  getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getStartHireDate() {
    return startHireDate;
  }

  public void setStartHireDate(LocalDate startHireDate) {
    this.startHireDate = startHireDate;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Employee{" +
        "id=" + id +
        ", first_name='" + firstName + '\'' +
        ", secondName='" + secondName + '\'' +
        ", team=" + team +
        ", startDate=" + startDate +
        ", startHireDate=" + startHireDate +
        '}';
  }

  @Override
  public int compareTo(Employee employee) {
    if (id == employee.id) {
      return 0;
    } else if (id > employee.id) {
      return 1;
    } else {
      return -1;
    }
  }
}

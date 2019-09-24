package com.infoshareacademy.gitloopersi.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@NamedQueries({
    @NamedQuery(
        name = "Employee.findAll",
        query = "SELECT e FROM Employee e"
    ),
    @NamedQuery(
        name = "Employee.findEmployeeByEmail",
        query = "SELECT e FROM Employee e WHERE e.email LIKE :email"
    )
})
@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  @NotBlank(message = "First name is required!")
  private String firstName;

  @Column(name = "second_name")
  @NotBlank(message = "Second name is required!")
  private String secondName;

  @Column(name = "email", unique = true)
  @NotBlank(message = "Email is required!")
  @Email(message = "Email should be valid!")
  private String email;


  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,
      CascadeType.PERSIST})
  @JoinColumn(name = "team_id")
  @NotNull(message = "Selecting a team is required!")
  private Team team;

  @Column(name = "start_date")
  @NotNull(message = "Employed from date is required!")
  @PastOrPresent(message = "The employed from date should be past or present!")
  private LocalDate startDate;

  @Column(name = "start_hire_date")
  @NotNull(message = "First employment date is required!")
  @PastOrPresent(message = "The first employment date should be past or present!")
  private LocalDate startHireDate;

  @Column(name = "admin")
  @NotNull(message = "Admin check have to be true or false")
  private boolean admin;

  @OneToMany(mappedBy = "employee",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Vacation> employeeVacations = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Team getTeam() {
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

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public List<Vacation> getEmployeeVacations() {
    return employeeVacations;
  }

  public void setEmployeeVacations(
      List<Vacation> employeeVacations) {
    this.employeeVacations = employeeVacations;
  }
}
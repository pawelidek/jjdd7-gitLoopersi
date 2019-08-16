package com.infoshareacademy.jjdd7.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Serializable {
    private String firstName;
    private String secondName;
    private Team team;
    private LocalDate startDate;
    private String email;

    public Employee(String firstName, String secondName, Team team, LocalDate startDate, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.team = team;
        this.startDate = startDate;
        this.email = email;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
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

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(secondName, employee.secondName) &&
                Objects.equals(team, employee.team) &&
                Objects.equals(startDate, employee.startDate) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, team, startDate, email);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", team=" + team +
                ", startDate=" + startDate +
                ", email='" + email + '\'' +
                '}';
    }
}

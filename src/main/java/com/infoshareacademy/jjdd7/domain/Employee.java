package com.infoshareacademy.jjdd7.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Employee implements Serializable {
    private String firstName;
    private String secondName;
    private Team team;
    private Date startDate;

    public Employee(String firstName, String secondName, Team team, Date startDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.team = team;
        this.startDate = startDate;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                secondName.equals(employee.secondName) &&
                team.equals(employee.team) &&
                startDate.equals(employee.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, team, startDate);
    }

    @Override
    public String toString() {
        return firstName +
                " " + secondName +
                ", " + team +
                ", " + startDate;
    }
}

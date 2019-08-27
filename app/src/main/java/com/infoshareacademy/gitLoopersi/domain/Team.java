package com.infoshareacademy.gitLoopersi.domain;

import java.io.Serializable;
import java.util.Objects;

public class Team implements Serializable {

  private String name;

  public Team(String name) {
    this.name = name;
  }

  public Team() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Team team = (Team) o;
    return name.equals(team.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
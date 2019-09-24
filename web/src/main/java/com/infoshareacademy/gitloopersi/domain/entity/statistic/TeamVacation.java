package com.infoshareacademy.gitloopersi.domain.entity.statistic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team_vacation")
public class TeamVacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "team_id", unique = true)
  @NotNull
  private Long teamId;

  @Column(name = "quantity")
  @NotNull
  private Integer quantity;

  public TeamVacation() {
  }

  public TeamVacation(@NotNull Long teamId,
      @NotNull Integer quantity) {
    this.teamId = teamId;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "TeamVacation{" +
        "id=" + id +
        ", teamId=" + teamId +
        ", quantity=" + quantity +
        '}';
  }
}

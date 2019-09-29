package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamVacationStatResponse {

  @JsonProperty("teamName")
  private String teamName;

  @JsonProperty("quantity")
  private Integer quantity;

  public TeamVacationStatResponse() {
  }

  public TeamVacationStatResponse(String teamName, Integer quantity) {
    this.teamName = teamName;
    this.quantity = quantity;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}

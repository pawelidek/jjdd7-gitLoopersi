package com.infoshareacademy.gitloopersi.domain.jsonapi.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamVacationResponse {

  @JsonProperty("teamName")
  private String teamName;

  @JsonProperty("quantity")
  private String quantity;

  public TeamVacationResponse() {
  }

  public TeamVacationResponse(String teamName, String quantity) {
    this.teamName = teamName;
    this.quantity = quantity;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
}

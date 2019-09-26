package com.infoshareacademy.gitloopersi.domain.jsonapi.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmplyeeVacationResponse {

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("quantity")
  private String quantity;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public EmplyeeVacationResponse() {
  }

  public EmplyeeVacationResponse(String fullName, String quantity) {
    this.fullName = fullName;
    this.quantity = quantity;
  }
}

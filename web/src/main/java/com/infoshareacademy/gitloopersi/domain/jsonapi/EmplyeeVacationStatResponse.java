package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmplyeeVacationStatResponse {

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

  public EmplyeeVacationStatResponse() {
  }

  public EmplyeeVacationStatResponse(String fullName, String quantity) {
    this.fullName = fullName;
    this.quantity = quantity;
  }
}

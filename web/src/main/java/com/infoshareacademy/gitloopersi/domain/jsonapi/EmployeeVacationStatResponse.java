package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeVacationStatResponse {

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("quantity")
  private Integer quantity;

  public EmployeeVacationStatResponse() {
  }

  public EmployeeVacationStatResponse(String fullName, Integer quantity) {
    this.fullName = fullName;
    this.quantity = quantity;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}

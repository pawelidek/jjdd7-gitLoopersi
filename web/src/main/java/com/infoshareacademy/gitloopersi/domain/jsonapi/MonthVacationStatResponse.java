package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthVacationStatResponse {

  @JsonProperty("month")
  private String month;

  @JsonProperty("quantity")
  private Integer quantity;

  public MonthVacationStatResponse() {
  }

  public MonthVacationStatResponse(String month, Integer quantity) {
    this.month = month;
    this.quantity = quantity;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}

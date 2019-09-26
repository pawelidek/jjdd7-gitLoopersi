package com.infoshareacademy.gitloopersi.domain.jsonapi.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthVacationResponse {

  @JsonProperty("month")
  private String month;

  @JsonProperty("quantity")
  private String quantity;

  public MonthVacationResponse() {
  }

  public MonthVacationResponse(String month, String quantity) {
    this.month = month;
    this.quantity = quantity;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
}

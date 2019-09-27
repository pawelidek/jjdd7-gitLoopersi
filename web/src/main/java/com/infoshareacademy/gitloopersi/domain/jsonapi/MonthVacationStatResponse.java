package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthVacationStatResponse {

  @JsonProperty("month")
  private String month;

  @JsonProperty("quantity")
  private String quantity;

  public MonthVacationStatResponse() {
  }

  public MonthVacationStatResponse(String month, String quantity) {
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

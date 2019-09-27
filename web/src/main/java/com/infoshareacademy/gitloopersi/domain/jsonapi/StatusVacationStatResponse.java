package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusVacationStatResponse {

  @JsonProperty("status")
  private String status;

  @JsonProperty("quantity")
  private String quantity;

  public StatusVacationStatResponse() {
  }

  public StatusVacationStatResponse(String status, String quantity) {
    this.status = status;
    this.quantity = quantity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
}

package com.infoshareacademy.gitloopersi.domain.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusVacationStatResponse {

  @JsonProperty("status")
  private String status;

  @JsonProperty("quantity")
  private Integer quantity;

  public StatusVacationStatResponse() {
  }

  public StatusVacationStatResponse(String status, Integer quantity) {
    this.status = status;
    this.quantity = quantity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}

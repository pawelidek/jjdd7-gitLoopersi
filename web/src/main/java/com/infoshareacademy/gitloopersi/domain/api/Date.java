package com.infoshareacademy.gitloopersi.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"datetime", "timezone"})

public class Date {

  @JsonProperty("iso")
  private String iso;

  public String getIso() {
    return iso;
  }

  public void setIso(String iso) {
    this.iso = iso;
  }

  @Override
  public String toString() {
    return "Date{" +
        "iso=" + iso +
        '}';
  }
}

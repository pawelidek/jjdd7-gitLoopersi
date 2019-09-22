package com.infoshareacademy.gitloopersi.types;

public enum StatusType {
  REQUESTED("Requested"), ACCEPTED("Accepted"), REJECTED("Rejected");
  private String type;

  StatusType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
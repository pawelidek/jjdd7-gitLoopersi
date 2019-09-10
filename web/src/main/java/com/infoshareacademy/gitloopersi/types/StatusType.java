package com.infoshareacademy.gitloopersi.types;

public enum StatusType {

  REQUESTED("REQUESTED"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED");
  private String type;

  StatusType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}

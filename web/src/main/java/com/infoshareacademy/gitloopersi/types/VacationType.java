package com.infoshareacademy.gitloopersi.types;

public enum VacationType {
  VACATION_LEAVE("VACATION_LEAVE"), CHILDCARE("CHILDCARE"), SPECIAL_LEAVE("SPECIAL_LEAVE");

  private String type;

  VacationType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}

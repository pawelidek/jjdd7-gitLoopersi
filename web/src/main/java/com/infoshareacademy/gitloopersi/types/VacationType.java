package com.infoshareacademy.gitloopersi.types;

public enum VacationType {
  VACATION_LEAVE("Vacation leave"), CHILDCARE("Childcare"), SPECIAL_LEAVE("Special leave");

  private String type;

  VacationType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}

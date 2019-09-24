package com.infoshareacademy.gitloopersi.types;

import java.util.Arrays;

public enum VacationType {
  VACATION_LEAVE("VACATION_LEAVE"), CHILDCARE("CHILDCARE"), SPECIAL_LEAVE("SPECIAL_LEAVE");

  private String type;

  VacationType(String type) {
    this.type = type;
  }

  public static VacationType valueOfType(String value) {
    return Arrays.stream(VacationType.values()).filter(vacationType -> vacationType.equals(value))
        .findFirst().orElse(null);
  }

  public String getType() {
    return type;
  }
}

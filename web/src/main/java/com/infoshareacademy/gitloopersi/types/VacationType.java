package com.infoshareacademy.gitloopersi.types;

import java.util.Arrays;
import java.util.Optional;

public enum VacationType {
  VACATION_LEAVE("Vacation leave"), CHILDCARE("Childcare"), SPECIAL_LEAVE("Special leave");

  private String type;

  VacationType(String type) {
    this.type = type;
  }

  public static VacationType valueOfType(String value) {

    return Optional.of(VacationType.values()).stream().flatMap(Arrays::stream)
        .filter(vacationType -> vacationType.type.equals(value)).findFirst().orElse(null);
  }

  public static boolean containsValueType(String value) {

    return Optional.of(VacationType.values()).stream().flatMap(Arrays::stream)
        .anyMatch(vacationType -> vacationType.type.equals(value));
  }

  public String getType() {
    return type;
  }
}

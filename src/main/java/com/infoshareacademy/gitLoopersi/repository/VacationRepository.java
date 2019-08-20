package com.infoshareacademy.gitLoopersi.repository;

import com.infoshareacademy.gitLoopersi.domain.Vacation;
import java.util.ArrayList;
import java.util.List;

public class VacationRepository {

  private static List<Vacation> allVacations = new ArrayList<>();

  public static List<Vacation> getAllVacations() {
    return allVacations;
  }

  public static void setAllVacations(
      List<Vacation> allVacations) {
    VacationRepository.allVacations = allVacations;
  }
}

package com.infoshareacademy.gitloopersi.repository;

import com.infoshareacademy.gitloopersi.domain.Vacation;
import java.util.ArrayList;
import java.util.List;

public class VacationRepository {

  private static List<Vacation> vacationList = new ArrayList<>();

  private VacationRepository() {
  }

  public static List<Vacation> getVacationList() {
    return vacationList;
  }

  public static void setVacationList(
      List<Vacation> vacationList) {
    VacationRepository.vacationList = vacationList;
  }
}

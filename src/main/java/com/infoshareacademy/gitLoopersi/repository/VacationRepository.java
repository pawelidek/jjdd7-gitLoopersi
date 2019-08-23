package com.infoshareacademy.gitLoopersi.repository;

import com.infoshareacademy.gitLoopersi.domain.Vacation;
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

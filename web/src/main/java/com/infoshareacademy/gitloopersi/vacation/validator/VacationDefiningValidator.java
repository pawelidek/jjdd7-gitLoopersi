package com.infoshareacademy.gitloopersi.vacation.validator;

import com.infoshareacademy.gitloopersi.vacation.handler.VacationDefiningHandler;
import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class VacationDefiningValidator {

  @Inject
  private VacationDefiningHandler vacationDefiningHandler;

  public void validateGivenDataToAdd(LocalDate dateFrom, LocalDate dateTo) {

  }

  public void validateGivenDataToDelete(LocalDate dateFrom, LocalDate dateTo) {

  }
}

package com.infoshareacademy.gitloopersi.vacation.service;

import com.infoshareacademy.gitloopersi.dao.VacationDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.vacation.validator.VacationDefiningValidator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class VacationDefiningService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private VacationDaoBean vacationDefiningDao;

  @Inject
  private VacationDefiningValidator vacationDefiningValidator;

  public void addVacation(Vacation vacation) {
    vacationDefiningDao.addVacation(vacation);
  }

  public void deleteVacation(Vacation vacation) {
    vacationDefiningDao.deleteVacation(vacation);
  }

  public List<Vacation> getVacationsList() {
    return vacationDefiningDao.getVacationsList();
  }

  public boolean isValidVacationRequestByEmployee(Long employeeId, String dateFrom, String dateTo) {

    if (isValidOverlappingOfDates(employeeId, dateFrom, dateTo)) {

      if (isValidTurnOfTheYear(dateFrom, dateTo)) {

        int numberOfVacationBank = getNumberOfVacationBank(employeeId);

        int numberOfSelectedVacationDays = getNumberOfSelectedVacationDays(dateFrom, dateTo);

        int numberOfRemainingVacationDays = getNumberOfRemainingVacationDays(employeeId,
            numberOfVacationBank, numberOfSelectedVacationDays);

        if (numberOfRemainingVacationDays > 0) {
          return true;
        } else {
          //error message
        }
      } else {
        //error message
      }
    } else {
      //error message
    }
    return true;
  }

  private int getNumberOfRemainingVacationDays(Long employeeId, int numberOfVacationBank,
      int numberOfSelectedVacationDays) {

    return vacationDefiningValidator
        .calculateRemainingVacationBank(employeeId, numberOfSelectedVacationDays,
            numberOfVacationBank);
  }

  private int getNumberOfSelectedVacationDays(String dateFrom, String dateTo) {

    return vacationDefiningValidator
        .calculateNumberOfSelectedVacationDays(dateFrom, dateTo);
  }

  private int getNumberOfVacationBank(Long employeeId) {

    return vacationDefiningValidator
        .calculateVacationBankForEmployee(employeeId);
  }

  private boolean isValidTurnOfTheYear(String dateFrom, String dateTo) {

    return vacationDefiningValidator.isValidTurnOfTheYear(dateFrom, dateTo);
  }

  private boolean isValidOverlappingOfDates(Long employeeId, String dateFrom, String dateTo) {

    return vacationDefiningValidator.isValidOverlappingOfDates(employeeId, dateFrom, dateTo);
  }

}

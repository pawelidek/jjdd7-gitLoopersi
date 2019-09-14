package com.infoshareacademy.gitloopersi.vacation.handler;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.service.EmployeeService;
import com.infoshareacademy.gitloopersi.service.HolidayService;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import com.infoshareacademy.gitloopersi.vacation.service.VacationDefiningService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class VacationDefiningHandler {

  private static final int YEARS_OF_EXPERIENCE = 10;
  private static final int LESS_THAN_TEN_YEARS_EXPERIENCE = 20;
  private static final int GREATER_THAN_TEN_YEARS_EXPERIENCE = 26;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private EmployeeService employeeService;

  @EJB
  private HolidayService holidayService;

  @EJB
  private VacationDefiningService vacationDefiningService;

  public int calculateVacationBankForEmployee(Long employeeId) {

    return YEARS_OF_EXPERIENCE > calculateEmployeeExperience(employeeId)
        ? LESS_THAN_TEN_YEARS_EXPERIENCE : GREATER_THAN_TEN_YEARS_EXPERIENCE;
  }

  public int calculateNumberOfSelectedVacationDays(String dateFrom, String dateTo) {

    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    logger.info("Calculate number of selected vacation days between {} and {} ", dateFromVacation,
        dateToVacation);

    List<Holiday> holidayList = holidayService.findAllHolidays();

    int amountOfHolidays = 0;
    int amountOfVacationDays = 0;

    for (Holiday holiday : holidayList) {
      if (holiday.getHolidayType() != HolidayType.NATIONAL_HOLIDAY) {
        continue;
      }
      for (LocalDate localDate = dateFromVacation; localDate.isBefore(dateToVacation);
          localDate = localDate.plusDays(1)) {
        if (localDate.equals(holiday.getDate())) {
          amountOfHolidays++;
          logger.info("Number of holidays between given dates = {}", amountOfHolidays);
          break;
        }
      }
    }
    for (LocalDate localDate = dateFromVacation; localDate.isBefore(dateToVacation.plusDays(1));
        localDate = localDate.plusDays(1)) {

      switch (localDate.getDayOfWeek()) {
        case SUNDAY:
        case SATURDAY:
          break;
        default:
          amountOfVacationDays++;
          logger
              .info("Number of sunday and saturday between given dates = {}", amountOfVacationDays);
      }
    }

    return amountOfVacationDays - amountOfHolidays;
  }

  public int calculateRemainingVacationBank(Long id, int numberOfSelectedVacationDays,
      int numberOfVacationBank) {

    LocalDate todayDate = LocalDate.now();
    int workDaysNumber = 0;
    int overdueDaysOff = 0;
    int monthCount = 12;

    Employee employee = employeeService.getEmployeeById(id);

    List<Vacation> countOfDaysHistory = vacationDefiningService.getVacationsList().stream()
        .filter(vacation -> vacation.getEmployee().getId().equals(id))
        .collect(Collectors.toList());

    for (Vacation vacation : countOfDaysHistory) {
      if (todayDate.getYear() == vacation.getDateFrom().getYear() && todayDate.getYear() == vacation
          .getDateTo().getYear()) {

        int numberOfRemainingDays = Optional.ofNullable(vacation.getDaysCount())
            .orElse(0);

        workDaysNumber = workDaysNumber + numberOfRemainingDays;
      }

      if (numberOfVacationBank == 20 && employee.getStartHireDate().getYear() == todayDate
          .minusYears(1).getYear()) {

        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (int) (overdueDaysOff - Math.floor(monthCount * 1.6));
      } else if (numberOfVacationBank == 26 && employee.getStartHireDate().getYear() == todayDate
          .minusYears(1).getYear()) {

        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (int) (overdueDaysOff - Math.floor(monthCount * 2.2));
      } else if (todayDate.minusYears(1).getYear() == vacation.getDateFrom().getYear()) {
        overdueDaysOff = overdueDaysOff + vacation.getDaysCount();
      }
    }

    overdueDaysOff = numberOfVacationBank - overdueDaysOff;
    workDaysNumber = (numberOfVacationBank - workDaysNumber) + overdueDaysOff;
    numberOfVacationBank = workDaysNumber - numberOfSelectedVacationDays;

    return numberOfVacationBank;
  }

  private int calculateEmployeeExperience(Long employeeId) {

    Employee employee = employeeService.getEmployeeById(employeeId);
    LocalDate today = LocalDate.now();

    return today.getYear() - employee.getStartDate().getYear();
  }
}

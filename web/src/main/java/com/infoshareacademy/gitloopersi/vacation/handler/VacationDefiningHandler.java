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
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class VacationDefiningHandler {

  @EJB
  private EmployeeService employeeService;

  @EJB
  private HolidayService holidayService;

  @EJB
  private VacationDefiningService vacationDefiningService;

  public int calculateVacationPoolForEmployee(Long employeeId) {

    Employee employee = employeeService.findEmployeeById(employeeId);
    LocalDate today = LocalDate.now();

    if ((today.getYear() - employee.getStartDate().getYear()) < 10) {
      return 20;
    } else {
      return 26;
    }
  }

  public int calculateNumberOfSelectedVacationDays(String dateFrom, String dateTo) {

    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

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
      }
    }

    return amountOfVacationDays - amountOfHolidays;
  }

  public int calculateRemainingVacationPool(Long id, int numberOfSelectedVacationDays,
      int numberOfVacationPool) {

    LocalDate todayDate = LocalDate.now();
    int workDaysNumber = 0;
    int overdueDaysOff = 0;
    int monthCount = 12;

    Employee employee = employeeService.findEmployeeById(id);

    List<Vacation> countOfDaysHistory = vacationDefiningService.getVacationsList().stream()
        .filter(vacation -> vacation.getEmployee().getId().equals(id))
        .collect(Collectors.toList());

    for (Vacation vacation : countOfDaysHistory) {
      if (todayDate.getYear() == vacation.getDateFrom().getYear() && todayDate.getYear() == vacation
          .getDateTo().getYear()) {

        workDaysNumber = workDaysNumber + vacation.getDaysCount();
      }

      if (numberOfVacationPool == 20 && employee.getStartHireDate().getYear() == todayDate
          .minusYears(1).getYear()) {

        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (int) (overdueDaysOff - Math.floor(monthCount * 1.6));
      } else if (numberOfVacationPool == 26 && employee.getStartHireDate().getYear() == todayDate
          .minusYears(1).getYear()) {

        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (int) (overdueDaysOff - Math.floor(monthCount * 2.2));
      } else if (todayDate.minusYears(1).getYear() == vacation.getDateFrom().getYear()) {
        overdueDaysOff = overdueDaysOff + vacation.getDaysCount();
      }
    }

    overdueDaysOff = numberOfVacationPool - overdueDaysOff;
    workDaysNumber = (numberOfVacationPool - workDaysNumber) + overdueDaysOff;
    numberOfVacationPool = workDaysNumber - numberOfSelectedVacationDays;

    return numberOfVacationPool;
  }
}

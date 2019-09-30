package com.infoshareacademy.gitloopersi.handler;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.holidaymanager.HolidayService;
import com.infoshareacademy.gitloopersi.service.propertiesmanager.PropertiesLoaderService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import com.infoshareacademy.gitloopersi.types.VacationType;
import java.io.IOException;
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

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private PropertiesLoaderService propertiesLoaderService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private HolidayService holidayService;

  @EJB
  private VacationDefiningService vacationDefiningService;

  public int calculateVacationPoolForEmployee(Long employeeId) throws IOException {

    return YEARS_OF_EXPERIENCE > calculateEmployeeExperience(employeeId)
        ? getPartVacationPool() : getFullVacationPool();
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

  public int calculateRemainingVacationPool(Long id, int numberOfSelectedVacationDays,
      int numberOfVacationPool) throws IOException {

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

      if (numberOfVacationPool == getPartVacationPool()
          && employee.getStartHireDate().getYear() == todayDate.minusYears(1).getYear()) {

        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (int) (overdueDaysOff - Math.floor(monthCount * 1.6));

      } else if (numberOfVacationPool == getFullVacationPool()
          && employee.getStartHireDate().getYear() == todayDate
          .minusYears(1).getYear()) {

        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (int) (overdueDaysOff - Math.floor(monthCount * 2.2));

      } else if (todayDate.minusYears(1).getYear() == vacation.getDateFrom().getYear()) {
        overdueDaysOff = overdueDaysOff + vacation.getDaysCount();
      }
      overdueDaysOff = numberOfVacationPool - overdueDaysOff;
    }

    workDaysNumber = (numberOfVacationPool - workDaysNumber) + overdueDaysOff;
    numberOfVacationPool = workDaysNumber - numberOfSelectedVacationDays;

    return numberOfVacationPool;
  }

  public int calculateVacationPoolChildcare(Long id, int numberOfSelectedVacationDays,
      int numberOfVacationPool) throws IOException {

    LocalDate todayDate = LocalDate.now();
    int workDaysNumber = 0;
    int overdueDaysOff = 0;

    List<Vacation> countOfDaysHistory = vacationDefiningService.getVacationsList().stream()
        .filter(vacation -> vacation.getEmployee().getId().equals(id)).filter(vacation ->
            vacation.getVacationType().getType().equals(VacationType.CHILDCARE.getType()))
        .collect(Collectors.toList());

    for (Vacation vacation : countOfDaysHistory) {
      if (todayDate.getYear() == vacation.getDateFrom().getYear() && todayDate.getYear() == vacation
          .getDateTo().getYear()) {

        int numberOfRemainingDays = Optional.ofNullable(vacation.getDaysCount())
            .orElse(0);

        workDaysNumber = workDaysNumber + numberOfRemainingDays;
      }

      if (todayDate.minusYears(1).getYear() == vacation.getDateFrom().getYear()) {
        overdueDaysOff = overdueDaysOff + vacation.getDaysCount();
      }
      overdueDaysOff = numberOfVacationPool - overdueDaysOff;
    }

    workDaysNumber = (numberOfVacationPool - workDaysNumber) + overdueDaysOff;
    numberOfVacationPool = workDaysNumber - numberOfSelectedVacationDays;

    return numberOfVacationPool;
  }

  public int calculateVacationPoolSpecialLeave(Long id, int numberOfSelectedVacationDays,
      int numberOfVacationPool) throws IOException {

    LocalDate todayDate = LocalDate.now();
    int workDaysNumber = 0;
    int overdueDaysOff = 0;

    List<Vacation> countOfDaysHistory = vacationDefiningService.getVacationsList().stream()
        .filter(vacation -> vacation.getEmployee().getId().equals(id)).filter(vacation ->
            vacation.getVacationType().getType().equals(VacationType.SPECIAL_LEAVE.getType()))
        .collect(Collectors.toList());

    for (Vacation vacation : countOfDaysHistory) {
      if (todayDate.getYear() == vacation.getDateFrom().getYear() && todayDate.getYear() == vacation
          .getDateTo().getYear()) {

        int numberOfRemainingDays = Optional.ofNullable(vacation.getDaysCount())
            .orElse(0);

        workDaysNumber = workDaysNumber + numberOfRemainingDays;
      }

      if (todayDate.minusYears(1).getYear() == vacation.getDateFrom().getYear()) {
        overdueDaysOff = overdueDaysOff + vacation.getDaysCount();
      }
      overdueDaysOff = numberOfVacationPool - overdueDaysOff;
    }

    workDaysNumber = (numberOfVacationPool - workDaysNumber) + overdueDaysOff;
    numberOfVacationPool = workDaysNumber - numberOfSelectedVacationDays;

    return numberOfVacationPool;
  }

  private int calculateEmployeeExperience(Long employeeId) {

    Employee employee = employeeService.getEmployeeById(employeeId);
    LocalDate today = LocalDate.now();

    return today.getYear() - employee.getStartDate().getYear();
  }

  private Integer getFullVacationPool() throws IOException {
    return Integer.valueOf(
        propertiesLoaderService.loadVacationProperties().getProperty("full.vacation.pool"));
  }

  private Integer getPartVacationPool() throws IOException {
    return Integer.valueOf(
        propertiesLoaderService.loadVacationProperties().getProperty("part.vacation.pool"));
  }
}

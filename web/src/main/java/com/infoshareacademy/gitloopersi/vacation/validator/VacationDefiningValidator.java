package com.infoshareacademy.gitloopersi.vacation.validator;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.vacation.handler.VacationDefiningHandler;
import com.infoshareacademy.gitloopersi.vacation.service.VacationDefiningService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class VacationDefiningValidator {

  @Inject
  private VacationDefiningHandler vacationDefiningHandler;

  @EJB
  private VacationDefiningService vacationDefiningService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());
  private LocalDate dateToday = LocalDate.now();
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public boolean isValidDateFrom(String dateFrom) {

    try {
      logger.info("Validate correct format dateFrom");
      simpleDateFormat.parse(dateFrom).toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();

    } catch (ParseException e) {
      logger.warn(e.getMessage());
      return false;
    }
    return true;
  }

  public boolean isValidDateTo(String dateTo) {

    try {
      logger.info("Validate correct format dateTo");
      simpleDateFormat.parse(dateTo).toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();

    } catch (ParseException e) {
      logger.warn(e.getMessage());
      return false;
    }
    return true;
  }

  public boolean isValidDateFromFuture(String dateFrom) {

    logger.info("Validate whether the dateFrom is from the future");
    LocalDate dateFromVacation = LocalDate.parse(dateFrom);

    Timestamp timestampDateFrom = Timestamp.valueOf(dateFromVacation.atTime(LocalTime.MIDNIGHT));
    Long dateFromCount = timestampDateFrom.getTime();

    Timestamp timestampToday = Timestamp.valueOf(dateToday.atTime(LocalTime.MIDNIGHT));
    Long todayCount = timestampToday.getTime();

    return todayCount < dateFromCount;
  }

  public boolean isValidDateToFuture(String dateTo) {

    logger.info("Validate whether the dateTo is from the future");
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    Timestamp timestampDateTo = Timestamp.valueOf(dateToVacation.atTime(LocalTime.MIDNIGHT));
    Long dateToCount = timestampDateTo.getTime();

    Timestamp timestampToday = Timestamp.valueOf(dateToday.atTime(LocalTime.MIDNIGHT));
    Long todayCount = timestampToday.getTime();

    return todayCount < dateToCount;
  }

  public boolean isValidDateFromBeforeDateTo(String dateFrom, String dateTo) {

    logger.info("Validate whether the dateFrom is before dateTo");
    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    Timestamp timestampDateFrom = Timestamp.valueOf(dateFromVacation.atTime(LocalTime.MIDNIGHT));
    Long dateFromCount = timestampDateFrom.getTime();

    Timestamp timestampDateTo = Timestamp.valueOf(dateToVacation.atTime(LocalTime.MIDNIGHT));
    Long dateToCount = timestampDateTo.getTime();

    return dateFromCount < dateToCount;
  }

  public boolean isValidOverlappingOfDates(Long id, String dateFrom, String dateTo) {

    List<Vacation> vacations = vacationDefiningService.getVacationsList().stream()
        .filter(vacation -> vacation.getEmployee().getId().equals(id))
        .collect(Collectors.toList());

    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    for (Vacation dateRange : vacations) {
      for (LocalDate existDate = dateRange.getDateFrom(); existDate.isBefore(dateRange.getDateTo());
          existDate = existDate.plusDays(1)) {
        for (LocalDate definingDate = dateFromVacation; definingDate.isBefore(dateToVacation);
            definingDate = definingDate.plusDays(1)) {
          if (definingDate.equals(existDate)) {
            logger.info("The dates given overlap {} - {}", dateFromVacation, dateToVacation);
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isValidTurnOfTheYear(String dateFrom, String dateTo) {

    LocalDate dateFromVacation = LocalDate.parse(dateFrom);
    LocalDate dateToVacation = LocalDate.parse(dateTo);

    return dateToday.getYear() == dateFromVacation.getYear()
        && dateToday.getYear() == dateToVacation.getYear();
  }

  public int calculateVacationBankForEmployee(Long employeeId) {
    return vacationDefiningHandler.calculateVacationBankForEmployee(employeeId);
  }

  public int calculateNumberOfSelectedVacationDays(String dateFrom, String dateTo) {
    return vacationDefiningHandler.calculateNumberOfSelectedVacationDays(dateFrom, dateTo);
  }

  public int calculateRemainingVacationBank(Long employeeId, int numberOfSelectedVacationDays,
      int numberOfVacationBank) {

    return vacationDefiningHandler
        .calculateRemainingVacationBank(employeeId, numberOfSelectedVacationDays,
            numberOfVacationBank);
  }
}
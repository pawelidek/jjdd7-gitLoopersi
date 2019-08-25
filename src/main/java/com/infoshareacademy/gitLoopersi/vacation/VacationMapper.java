package com.infoshareacademy.gitLoopersi.vacation;

import static java.lang.Math.ceil;
import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.domain.Holiday;
import com.infoshareacademy.gitLoopersi.domain.Vacation;
import com.infoshareacademy.gitLoopersi.parser.Parser;
import com.infoshareacademy.gitLoopersi.parser.TypeOfHoliday;
import com.infoshareacademy.gitLoopersi.properties.AppConfig;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.repository.HolidayRepository;
import com.infoshareacademy.gitLoopersi.repository.VacationRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VacationMapper {

  private Long vacationDateFromCounter;
  private Long vacationDateToCounter;

  public void validateDataForDefineVacation() {

    VacationService vacationService = new VacationService();
    List<Vacation> listVacations = VacationRepository.getVacationList();

    Scanner scanner = new Scanner(System.in);

    LocalDate vacationDateFrom;
    LocalDate vacationDateTo;
    System.out.println("Main menu >> Vacation >> Add vacation");
    System.out.println("\nEnter your ID: ");
    String idToCheck = scanner.nextLine();
    while (!isCreatable(idToCheck)) {
      System.out.println("Wrong data! Enter your ID: ");
      idToCheck = scanner.nextLine();
    }

    Long id = Long.valueOf(idToCheck);

    long employeeExist = EmployeeRepository.getEmployeeList().stream()
        .filter(employee -> employee.getId().equals(id))
        .count();

    if (employeeExist != 0) {

      Employee employee = EmployeeRepository.getEmployeeList().stream()
          .filter(emp -> emp.getId().equals(id))
          .findFirst().get();

      int numberOfVacationDays = checkAmountDaysOff(employee);

      vacationDateFrom = validateDateFrom();
      vacationDateTo = validateDateTo();

      validateOverlappingOfTheGivenDateRange(id, vacationDateFrom, vacationDateTo);
      validateAtTheTurnOfTheYear(vacationDateFrom, vacationDateTo);

      int amountOfDays = validateAndCalculateVacationDays(vacationDateFrom, vacationDateTo);

      validateRemainingDaysOff(id, numberOfVacationDays, amountOfDays);

      listVacations.add(new Vacation(id, vacationDateFrom, vacationDateTo, amountOfDays));
      vacationService.addVacation(listVacations);
    } else {
      System.out.println("An employee with a given ID does not exist \n"
          + "Enter a valid employee ID");
      System.out.println("\nType '0' to return or 'Enter' to add define vacation.");
    }
  }

  public void validateCancellationOfVacation() {

    VacationService vacationService = new VacationService();

    Scanner scanner = new Scanner(System.in);

    LocalDate vacationDateFrom;
    LocalDate vacationDateTo;
    System.out.println("Main menu >> Vacation >> Cancel vacation");
    System.out.println("\nEnter your ID: ");
    String idToCheck = scanner.nextLine();
    while (!isCreatable(idToCheck)) {
      System.out.println("Wrong data! Enter your ID: ");
      idToCheck = scanner.nextLine();
    }

    Long id = Long.valueOf(idToCheck);

    long employeeExist = EmployeeRepository.getEmployeeList().stream()
        .filter(employee -> employee.getId().equals(id))
        .count();

    if (employeeExist != 0) {

      vacationDateFrom = validateDateFrom();
      vacationDateTo = validateDateTo();

      List<Vacation> vacationList = VacationRepository.getVacationList().stream()
          .filter(vacation -> vacation.getEmployeeId().equals(id))
          .collect(Collectors.toList());

      for (Vacation vacation : vacationList) {
        if (vacationDateFrom.equals(vacation.getDateFrom()) && vacationDateTo
            .equals(vacation.getDateTo())) {
          vacationService.cancelVacation(vacation);
          break;
        } else {
          System.out.println("If you do not have a vacation within the given date range,"
              + " please define the exact date range.");
          System.out.println("\nType '0' to return or 'Enter' to cancel vacation.");
          break;
        }
      }
    } else {
      System.out.println("An employee with a given ID does not exist \n"
          + "Enter a valid employee ID");
      System.out.println("\nType '0' to return or 'Enter' to cancel vacation.");
    }
  }

  private LocalDate validateDateFrom() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate vacationDateFrom;

    System.out.println("Enter vacation date from (Format: " + AppConfig.getDateFormat() + "): ");

    LocalDate today = LocalDate.now();
    Timestamp timestampToday = Timestamp.valueOf(today.atTime(LocalTime.MIDNIGHT));
    Long todayDate = timestampToday.getTime();

    do {
      String vacationDateFromString = scanner.nextLine();

      try {
        vacationDateFrom = simpleDateFormat.parse(vacationDateFromString)
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (vacationDateFrom.getYear() == today.getYear() || vacationDateFrom.getYear() == today
            .plusYears(1).getYear()) {
          Timestamp timestampVacationFrom = Timestamp
              .valueOf(vacationDateFrom.atTime(LocalTime.MIDNIGHT));
          setVacationDateFromCounter(timestampVacationFrom.getTime());

          while (todayDate > getVacationDateFromCounter()) {

            System.out.println("Wrong data! Give the date from the future: ");
            vacationDateFromString = scanner.nextLine();
            vacationDateFrom = simpleDateFormat.parse(vacationDateFromString)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            timestampVacationFrom = Timestamp
                .valueOf(vacationDateFrom.atTime(LocalTime.MIDNIGHT));
            setVacationDateFromCounter(timestampVacationFrom.getTime());
          }
        } else {
          System.out.println(
              "You can define or cancel your vacation from today until the end of next year");
          vacationDateFrom = null;
        }
      } catch (ParseException e) {
        vacationDateFrom = null;
        System.out.println(
            "Wrong data! Please enter data in format " + AppConfig.getDateFormat() + "): ");
      }
    } while (vacationDateFrom == null);

    return vacationDateFrom;
  }

  private LocalDate validateDateTo() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate vacationDateTo;
    LocalDate today = LocalDate.now();

    System.out.println("Enter vacation date to (Format: " + AppConfig.getDateFormat() + "): ");
    do {
      String vacationDateToString = scanner.nextLine();
      try {
        vacationDateTo = simpleDateFormat.parse(vacationDateToString)
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (vacationDateTo.getYear() == today.getYear() || vacationDateTo.getYear() == today
            .plusYears(1).getYear()) {
          Timestamp timestampVacationTo = Timestamp
              .valueOf(vacationDateTo.atTime(LocalTime.MIDNIGHT));

          setVacationDateToCounter(timestampVacationTo.getTime());

          while (getVacationDateToCounter() < getVacationDateFromCounter()) {

            System.out.println("Wrong data! Give the date from the future: ");
            vacationDateToString = scanner.nextLine();

            vacationDateTo = simpleDateFormat.parse(vacationDateToString)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            timestampVacationTo = Timestamp
                .valueOf(vacationDateTo.atTime(LocalTime.MIDNIGHT));
            setVacationDateToCounter(timestampVacationTo.getTime());
          }
        } else {
          System.out.println(
              "You can define or cancel your vacation from today until the end of next year");
          vacationDateTo = null;
        }
      } catch (ParseException e) {
        vacationDateTo = null;
        System.out.println(
            "Wrong data! Please enter data in format " + AppConfig.getDateFormat() + " : ");
      }
    } while (vacationDateTo == null);

    return vacationDateTo;
  }

  private int checkAmountDaysOff(Employee employee) {

    LocalDate today = LocalDate.now();
    LocalDate startCareer = employee.getStartDate();
    Period period = Period.between(startCareer, today);

    if (period.getYears() < 10) {
      return 20;
    } else {
      return 26;
    }
  }

  private int validateAndCalculateVacationDays(LocalDate vacationDateFrom,
      LocalDate vacationDateTo) {

    Parser parser = new Parser();
    List<Holiday> holidayList = HolidayRepository.getAllHolidays();

    int amountOfDaysOff = 0;
    int amountOfHolidays = 0;

    for (Holiday holiday : holidayList) {
      if (holiday.getTypeOfHoliday() != TypeOfHoliday.NATIONAL_HOLIDAY) {
        continue;
      }
      for (LocalDate localDate = vacationDateFrom; localDate.isBefore(vacationDateTo);
          localDate = localDate.plusDays(1)) {
        if (localDate.equals(holiday.getDate())) {
          amountOfHolidays++;
          break;
        }
      }
    }
    for (LocalDate localDate = vacationDateFrom; localDate.isBefore(vacationDateTo.plusDays(1));
        localDate = localDate.plusDays(1)) {
      switch (localDate.getDayOfWeek()) {
        case SUNDAY:
        case SATURDAY:
          break;
        default:
          amountOfDaysOff++;
      }
    }

    return amountOfDaysOff - amountOfHolidays;
  }

  private void validateRemainingDaysOff(Long id, int numberOfVacationDays, int amountOfDays) {

    LocalDate today = LocalDate.now();
    int workDaysCount = 0;
    int overdueDaysOff = 0;
    int monthCount = 12;

    Employee employee = EmployeeRepository.getEmployeeList().stream()
        .filter(emp -> emp.getId().equals(id))
        .findFirst().get();

    List<Vacation> countOfDaysHistory = VacationRepository.getVacationList().stream()
        .filter(vacation -> vacation.getEmployeeId().equals(id))
        .collect(Collectors.toList());
    System.out.println(countOfDaysHistory);
    for (Vacation value : countOfDaysHistory) {
      if (today.getYear() == value.getDateFrom().getYear() && today.getYear() == value.getDateTo()
          .getYear()) {

        workDaysCount = workDaysCount + value.getCountOfDays();
      }

      if (numberOfVacationDays == 20
          && employee.getStartHireDate().getYear() == today.minusYears(1).getYear()) {
        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = (overdueDaysOff - (int) (ceil(monthCount * 1.6)));
      } else if (numberOfVacationDays == 26
          && employee.getStartHireDate().getYear() == today.minusYears(1).getYear()) {
        monthCount = monthCount - employee.getStartHireDate().getMonthValue();
        overdueDaysOff = overdueDaysOff - (int) (ceil(monthCount * 2.2));
      } else if (today.minusYears(1).getYear() == value.getDateFrom().getYear()) {
        overdueDaysOff = overdueDaysOff + value.getCountOfDays();
      }
    }

    overdueDaysOff = numberOfVacationDays - overdueDaysOff;
    workDaysCount = (numberOfVacationDays - workDaysCount) + overdueDaysOff;
    numberOfVacationDays = workDaysCount - amountOfDays;

    if (numberOfVacationDays < 0) {
      validateLackOfDaysOff(workDaysCount);
      validateDataForDefineVacation();
    }
  }

  private void validateAtTheTurnOfTheYear(LocalDate vacationDateFrom,
      LocalDate vacationDateTo) {

    LocalDate today = LocalDate.now();

    if (today.getYear() != vacationDateFrom.getYear() || today.getYear() != vacationDateTo
        .getYear()) {

      System.out.println(
          "In order to define vacation at the turn of the year, set the scope of"
              + " leave first for the current year and then for the following year.");
      validateDataForDefineVacation();
    }
  }

  private void validateLackOfDaysOff(int amountOfDays) {
    System.out.println(
        "You don't have enough vacation days to go on vacation in the specified period."
            + " The remaining number of vacation days is: " + amountOfDays);
  }

  private void validateOverlappingOfTheGivenDateRange(Long id, LocalDate vacationDateFrom,
      LocalDate vacationDateTo) {

    List<Vacation> employeeVacationList = VacationRepository.getVacationList().stream()
        .filter(vacation -> vacation.getEmployeeId().equals(id))
        .collect(Collectors.toList());
    boolean vacationFlag = false;

    for (Vacation dateRange : employeeVacationList) {
      for (LocalDate localDate = dateRange.getDateFrom(); localDate.isBefore(dateRange.getDateTo());
          localDate = localDate.plusDays(1)) {
        for (LocalDate localDate1 = vacationDateFrom; localDate1.isBefore(vacationDateTo);
            localDate1 = localDate1.plusDays(1)) {
          if (localDate1.equals(localDate)) {
            vacationFlag = true;
          }
        }
      }
    }

    if (vacationFlag) {
      System.out.println("The given range of dates is superimposed on the already reported "
          + "leave, please enter the range correctly.");
      validateDataForDefineVacation();
    }
  }

  public Long getVacationDateFromCounter() {
    return vacationDateFromCounter;
  }

  public void setVacationDateFromCounter(Long vacationDateFromCounter) {
    this.vacationDateFromCounter = vacationDateFromCounter;
  }

  public Long getVacationDateToCounter() {
    return vacationDateToCounter;
  }

  public void setVacationDateToCounter(Long vacationDateToCounter) {
    this.vacationDateToCounter = vacationDateToCounter;
  }
}
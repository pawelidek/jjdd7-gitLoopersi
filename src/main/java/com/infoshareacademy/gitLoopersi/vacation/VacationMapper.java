package com.infoshareacademy.gitLoopersi.vacation;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.domain.Holiday;
import com.infoshareacademy.gitLoopersi.domain.Vacation;
import com.infoshareacademy.gitLoopersi.parser.Parser;
import com.infoshareacademy.gitLoopersi.parser.TypeOfHoliday;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.repository.HolidayRepository;
import com.infoshareacademy.gitLoopersi.repository.VacationRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VacationMapper {

  private Long vacationDateFromCounter;
  private Long vacationDateToCounter;

  public void validateDataForDefineVacation() {

    VacationService vacationService = new VacationService();
    List<Vacation> listVacations = VacationRepository.getAllVacations();

    Scanner scanner = new Scanner(System.in);
    
    LocalDate vacationDateFrom = null;
    LocalDate vacationDateTo = null;

    System.out.println("Enter your ID: \n");
    String idToCheck = scanner.nextLine();
    while (!isCreatable(idToCheck)) {
      System.out.println("Wrong data! Enter your ID: \n");
      idToCheck = scanner.nextLine();
    }

    Long id = Long.valueOf(idToCheck);

    long employeeExist = EmployeeRepository.getAllEmployees().stream()
        .filter(employee -> employee.getId().equals(id))
        .count();

    List<Employee> emp = EmployeeRepository.getAllEmployees().stream()
        .filter(employee -> employee.getId().equals(id))
        .collect(Collectors.toList());

    String email = emp.get(0).getEmail();
    int numberOfVacationDays = 0;
    Vacation vacationDays = new Vacation(numberOfVacationDays);
    if (!listVacations.contains(vacationDays)) {
      numberOfVacationDays = checkAmountDaysOff(emp.get(0));
    }

    if (employeeExist == 1) {

      vacationDateFrom = validateDateFrom();
      vacationDateTo = validateDateTo();

    } else {
      System.out.println("Employee not found with this ID number.");
    }

    numberOfVacationDays = validateAndCalculateVacationDays(vacationDateFrom, vacationDateTo, numberOfVacationDays);

    System.out.println("id " + id + " email " + email + " vacationDateFrom " + vacationDateFrom +
        " vacationDateTo " + vacationDateTo + " numberOfVacationDays " + numberOfVacationDays);
    listVacations
        .add(new Vacation(id, email, vacationDateFrom, vacationDateTo, numberOfVacationDays));
    vacationService.addVacation(listVacations);

  }

  private LocalDate validateDateFrom() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
    LocalDate vacationDateFrom = null;

    System.out.println("Enter vacation date from (Format: yyyy.MM.dd): ");

    LocalDate localDate = LocalDate.now();
    Timestamp timestampToday = Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT));
    Long todayDate = timestampToday.getTime();

    do {
      String vacationDateFromString = scanner.nextLine();

      try {
        vacationDateFrom = simpleDateFormat.parse(vacationDateFromString)
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

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
      } catch (ParseException e) {
        vacationDateFrom = null;
        System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
      }
    } while (vacationDateFrom == null);

    return vacationDateFrom;
  }

  private LocalDate validateDateTo() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
    LocalDate vacationDateTo = null;

    System.out.println("Enter vacation date to (Format: yyyy.MM.dd): ");
    do {
      String vacationDateToString = scanner.nextLine();
      try {
        vacationDateTo = simpleDateFormat.parse(vacationDateToString)
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

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
      } catch (ParseException e) {
        vacationDateTo = null;
        System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
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

  private int validateAndCalculateVacationDays(LocalDate vacationDateFrom, LocalDate vacationDateTo,
      int numberOfVacationDays) {
    Parser parser = new Parser();
    List<Holiday> holidayList = HolidayRepository.getAllHolidays();

    int amountOfDaysOff = 1;
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
    for (LocalDate localDate = vacationDateFrom; localDate.isBefore(vacationDateTo);
        localDate = localDate.plusDays(1)) {
      switch (localDate.getDayOfWeek()) {
        case SUNDAY:
          break;
        case SATURDAY:
          break;
        default:
          amountOfDaysOff++;
      }
    }

    int count = amountOfDaysOff - amountOfHolidays;
    numberOfVacationDays = numberOfVacationDays - count;
    return numberOfVacationDays;
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
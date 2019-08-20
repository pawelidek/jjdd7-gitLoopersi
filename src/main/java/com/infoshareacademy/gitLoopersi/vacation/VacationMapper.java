package com.infoshareacademy.gitLoopersi.vacation;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.employee.EmployeeService;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Scanner;

public class VacationMapper {

  private Long vacationDateFromCounter;
  private Long vacationDateToCounter;

  public void validateDataForDefineVacation() {

    Scanner scanner = new Scanner(System.in);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    LocalDate vacationDateFrom = null;
    LocalDate vacationDateTo = null;

    System.out.println("Enter your ID: \n");
    String idToCheck = scanner.nextLine();
    while (!isCreatable(idToCheck)) {
      System.out.println("Wrong data! Enter your ID: \n");
      idToCheck = scanner.nextLine();
    }

    Long id = Long.valueOf(idToCheck);
    EmployeeRepository.getAllEmployees().stream()
        .filter(employee -> employee.getId().equals(id));





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

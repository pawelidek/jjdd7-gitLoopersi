package com.infoshareacademy.gitloopersi.vacation;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitloopersi.domain.Employee;
import com.infoshareacademy.gitloopersi.domain.Vacation;
import com.infoshareacademy.gitloopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitloopersi.properties.AppConfig;
import com.infoshareacademy.gitloopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitloopersi.repository.VacationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VacationSearchEngine {

  private ArrayList<Employee> listOfMatchingEmployees;
  private Employee searchedEmployee;
  private Predicate<Vacation> myFilterFrom;
  private Predicate<Vacation> myFilterTo;
  private LocalDate from;
  private LocalDate to;


  public void searchEmployeeVacation() {

    System.out.println("Main menu >> Search engine >> Employee vacation");

    if (EmployeeRepository.getEmployeeList().size() != 0) {
      searchForEmployee();
      ConsoleCleaner.cleanConsole();
      System.out.println("Main menu >> Search engine >> Employee vacation");

      if (getSearchedEmployee() == null) {
        System.out.println("\nThere is no matching employee!");

      } else {
        makeFilter();
        ConsoleCleaner.cleanConsole();

        System.out.println("Main menu >> Search engine >> Employee vacation\n");

        showListOfEmployeeVacation();
      }

    } else {
      System.out.println("\nThere are no employees added to database!");
    }

    System.out.println("\nType '0' to return");
    System.out.println("\nType \"exit\" to close the app");
  }

  private void searchForEmployee() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("\nEnter at least three signs of searched employee name: ");

    String searchedPhrase = scanner.nextLine().toLowerCase();
    int characterQuantity = searchedPhrase.length();

    while (characterQuantity < 3) {
      System.out.println("Please enter at least three signs! Enter searched phrase: ");
      searchedPhrase = scanner.nextLine().toLowerCase();
      characterQuantity = searchedPhrase.length();
    }

    String finalSearchedPhrase = searchedPhrase;

    listOfMatchingEmployees = (ArrayList<Employee>) EmployeeRepository.getEmployeeList()
        .stream()
        .filter(
            e -> (e.getFirstName().concat(" " + e.getSecondName())).toLowerCase()
                .contains(finalSearchedPhrase))
        .sorted()
        .collect(Collectors.toList());

    if (listOfMatchingEmployees.size() == 0) {
      searchedEmployee = null;

    } else if (listOfMatchingEmployees.size() == 1) {
      searchedEmployee = listOfMatchingEmployees.get(0);

    } else {
      showListOfMatchingEmployees();
      pickEmployeeFromList(scanner);
    }
  }

  private void makeFilter() {
    System.out.println("\nDo you want to filter results by date? Y/N");

    Scanner scanner = new Scanner(System.in);
    String answer = scanner.nextLine().toLowerCase();

    if ("y".equals(answer)) {

      this.from = validateFilterDateFrom().plusDays(-1);
      this.to = validateFilterDateTo().plusDays(1);
      this.myFilterFrom = (vacation -> vacation.getDateFrom().isAfter(from));
      this.myFilterTo = (vacation -> vacation.getDateTo().isBefore(to));
    }
  }

  private void showListOfEmployeeVacation() {
    String searchedEmployee = getSearchedEmployee()
        .getFirstName().concat(" " + getSearchedEmployee().getSecondName())
        .toUpperCase();

    Long searchedEmployeeId = getSearchedEmployee().getId();

    System.out.println("PLANNED VACATION OF: " + searchedEmployee);
    if (myFilterFrom != null || myFilterTo != null) {
      System.out.println("From " + from.plusDays(1) + " to " + to.plusDays(-1));
    }

    List<Vacation> searchedEmployeeVacationList;

    if (myFilterFrom == null || myFilterTo == null) {

      searchedEmployeeVacationList = VacationRepository.getVacationList().stream()
          .filter(vacation -> vacation.getEmployeeId().equals(searchedEmployeeId))
          .collect(Collectors.toList());

    } else {

      searchedEmployeeVacationList = VacationRepository.getVacationList().stream()
          .filter(vacation -> vacation.getEmployeeId().equals(searchedEmployeeId))
          .filter(myFilterFrom)
          .filter(myFilterTo)
          .collect(Collectors.toList());
    }

    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    System.out.println(
        AsciiTable.getTable(borderStyle, searchedEmployeeVacationList,
            Arrays.asList(
                createColumnVacation("Index",
                    i -> String
                        .valueOf(VacationRepository.getVacationList()
                            .indexOf(i) + 1)),
                createColumnVacation("Vacation start date", vacation -> vacation.getDateFrom()
                    .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))),
                createColumnVacation("Vacation end date", vacation -> vacation.getDateTo()
                    .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))),
                createColumnVacation("Duration",
                    vacation -> String.valueOf(vacation.getCountOfDays()))
            )));
  }

  private ColumnData<Vacation> createColumnVacation(String name,
      Function<Vacation, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }

  private void showListOfMatchingEmployees() {

    System.out.println("\nDid you think of...? ");
    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    System.out.println(
        AsciiTable.getTable(borderStyle, getListOfMatchingEmployees(), Arrays.asList(
            createColumn("Index",
                employee -> String
                    .valueOf(getListOfMatchingEmployees()
                        .indexOf(employee) + 1)),
            createColumn("Id", employee -> String.valueOf(employee.getId())),
            createColumn("Name", Employee::getFirstName),
            createColumn("Last Name", Employee::getSecondName),
            createColumn("Team", employee -> employee.getTeam().toString())
        )));
  }

  private ColumnData<Employee> createColumn(String name,
      Function<Employee, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }

  private void pickEmployeeFromList(Scanner scanner) {
    System.out.println("Enter the index of an employee you wanted to type: ");

    boolean isPickCorrect = false;

    do {
      String pickToCheck = scanner.nextLine();

      while (!isCreatable(pickToCheck)) {
        System.out.print("Wrong data! Enter " +
            "index of an employee you wanted to type: \n");
        pickToCheck = scanner.nextLine();
      }

      int pick = Integer.parseInt(pickToCheck);

      if ((pick > 0) && (pick <= (getListOfMatchingEmployees().size()))) {

        searchedEmployee = getListOfMatchingEmployees().get(pick - 1);
        isPickCorrect = true;

      } else {
        System.out.print("There is no such an index! Enter " +
            "correct index of an employee you wanted to type: \n");
      }
    } while (!isPickCorrect);
  }

  private LocalDate validateFilterDateFrom() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate vacationDateFrom;

    System.out.println("Enter vacation date from (Format: " + AppConfig.getDateFormat() + "): ");

    do {
      String vacationDateFromString = scanner.nextLine();

      try {
        vacationDateFrom = simpleDateFormat.parse(vacationDateFromString)
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (vacationDateFrom.isBefore(getSearchedEmployee().getStartHireDate())) {
          System.out.println("Wrong data! Give the date not older than hire date: ");
          vacationDateFromString = scanner.nextLine();

          vacationDateFrom = simpleDateFormat.parse(vacationDateFromString)
              .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

      } catch (ParseException e) {
        vacationDateFrom = null;
        System.out.println(
            "Wrong data! Please enter data in format " + AppConfig.getDateFormat() + "): ");
      }
    } while (vacationDateFrom == null);

    return vacationDateFrom;
  }

  private LocalDate validateFilterDateTo() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate vacationDateTo;

    System.out.println("Enter vacation date to (Format: " + AppConfig.getDateFormat() + "): ");
    do {
      String vacationDateToString = scanner.nextLine();
      try {
        vacationDateTo = simpleDateFormat.parse(vacationDateToString)
            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (vacationDateTo.isBefore(from.plusDays(1))) {
          System.out.println("Wrong data! Give the date later than the filter start date: ");
          vacationDateToString = scanner.nextLine();

          vacationDateTo = simpleDateFormat.parse(vacationDateToString)
              .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

      } catch (ParseException e) {
        vacationDateTo = null;
        System.out.println(
            "Wrong data! Please enter data in format " + AppConfig.getDateFormat() + " : ");
      }
    } while (vacationDateTo == null);

    return vacationDateTo;
  }

  private List<Employee> getListOfMatchingEmployees() {
    return listOfMatchingEmployees;
  }

  private Employee getSearchedEmployee() {
    return searchedEmployee;
  }
}

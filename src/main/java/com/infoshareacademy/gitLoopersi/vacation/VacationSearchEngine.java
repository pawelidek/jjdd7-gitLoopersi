package com.infoshareacademy.gitLoopersi.vacation;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.domain.Vacation;
import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.repository.VacationRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VacationSearchEngine {

  private ArrayList<Employee> listOfMatchingEmployees;
  private Employee searchedEmployee;

  public void searchEmployeeVacation() {

    if (EmployeeRepository.getEmployeeList().size() != 0) {
      searchForEmployee();

      ConsoleCleaner.cleanConsole();

      getSearchedEmployee();

      if (getSearchedEmployee() == null) {

        System.out.println("There is no matching employee!");

      } else {

        String searchedEmployee = getSearchedEmployee()
            .getFirstName().concat(" " + getSearchedEmployee().getSecondName())
            .toUpperCase();

        Long searchedEmployeeId = getSearchedEmployee().getId();

        System.out.println("PLANNED VACATION OF: " + searchedEmployee);

        Character[] borderStyle = AsciiTable.FANCY_ASCII;

        List<Vacation> searchedEmployeeVacationList = VacationRepository.getVacationList().stream()
            .filter(vacation -> vacation.getEmployeeId().equals(searchedEmployeeId))
            .collect(Collectors.toList());

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

      System.out.println("\n0. Return");

    } else {
      System.out.println("There are no employees added to database!");
      System.out.println("\nType '0' to return.");
    }
  }

  private ColumnData<Vacation> createColumnVacation(String name,
      Function<Vacation, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }

  void searchForEmployee() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter at least three signs of searched employee name: ");

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

  private List<Employee> getListOfMatchingEmployees() {
    return listOfMatchingEmployees;
  }

  private Employee getSearchedEmployee() {
    return searchedEmployee;
  }
}

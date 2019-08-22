package com.infoshareacademy.gitLoopersi.vacation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.employee.EmployeeService;
import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class EmployeeVacationSearchEngine {

  public void searchEmployeeVacation() {

    EmployeeSearchEngine employeeSearchEngine = new EmployeeSearchEngine();
    EmployeeService employeeService = new EmployeeService();

    employeeService.loadEmployeeData();
    employeeSearchEngine.searchForEmployee();

    ConsoleCleaner.cleanConsole();

    System.out.println("PLANNED VACATION OF: " +
        employeeSearchEngine.getSearchedEmployee()
            .getFirstName().concat(" " + employeeSearchEngine.getSearchedEmployee().getSecondName())
            .toUpperCase());

    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    System.out.println(
        AsciiTable.getTable(borderStyle, employeeSearchEngine.getListOfMatchingEmployees(),
            Arrays.asList(
                createColumn("Index",
                    employee -> String
                        .valueOf(EmployeeRepository
                            .getAllEmployees()
                            .indexOf(employee) + 1)),
                createColumn("Vacation start date", employee -> employee.getStartDate().format(
                    DateTimeFormatter.ofPattern("yyyy.MM.dd"))),
                createColumn("Vacation end date", employee -> employee.getStartDate().format(
                    DateTimeFormatter.ofPattern("yyyy.MM.dd"))),
                createColumn("Duration", Employee::getFirstName)
            )));

    System.out.println("\n1. Set date range to filter");
    System.out.println("0. Return");

    Scanner scanner = new Scanner(System.in);
    String readValue = scanner.nextLine();

    if (readValue.equals("0")) {
      ;
    } else if (readValue.equals("1")) {
      ;
    } else if (readValue.length() == 1) {
      ;
    }

  }

  private ColumnData<Employee> createColumn(String name,
      Function<Employee, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }
}
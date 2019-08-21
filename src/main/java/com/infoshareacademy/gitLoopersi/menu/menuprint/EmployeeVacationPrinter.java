package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.vacation.EmployeeVacationSearcher;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Function;

public class EmployeeVacationPrinter implements Menu {

  @Override
  public void doAction() {
    EmployeeVacationSearcher employeeVacationSearcher = new EmployeeVacationSearcher();

    System.out.println("Planned vacation of: " + employeeVacationSearcher.getSearchedEmployee());

    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    System.out.println(
        AsciiTable.getTable(borderStyle, employeeVacationSearcher.getListOfMatchingEmployees(),
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

    System.out.println("\n1. Find an employee");
    System.out.println("2. Set date range to filter");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
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
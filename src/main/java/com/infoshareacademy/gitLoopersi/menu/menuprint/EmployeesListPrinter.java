package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.employee.EmployeeService;
import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.properties.AppConfig;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class EmployeesListPrinter implements Menu {

  @Override
  public void doAction() {

    EmployeeService employeeService = new EmployeeService();
    employeeService.loadEmployeeData();

//      Copyright 2017 freva
//      Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
//      documentation files (the "Software"), to deal in the Software without restriction, including without limitation
//      the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
//      to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    List<Employee> allEmployees = EmployeeRepository.getEmployeeList();
    if (AppConfig.getSort().equals("ASC")) {
      Collections.sort(allEmployees);
    } else {
      Collections.reverse(allEmployees);
    }

    System.out.println(
        AsciiTable.getTable(borderStyle, allEmployees, Arrays.asList(
            createColumn("Index",
                employee -> String
                    .valueOf(allEmployees
                        .indexOf(employee) + 1)),
            createColumn("Id", employee -> String.valueOf(employee.getId())),
            createColumn("Name", Employee::getFirstName),
            createColumn("Last Name", Employee::getSecondName),
            createColumn("Team", employee -> employee.getTeam().toString()),
            createColumn("Work start date", employee -> employee.getStartDate().format(
                DateTimeFormatter.ofPattern(AppConfig.getDateFormat()))),
            createColumn("Hire date", employee -> employee.getStartHireDate().format(
                DateTimeFormatter.ofPattern(AppConfig.getDateFormat())))
        )));

    System.out.println("\n1. Add worker");
    System.out.println("2. Delete worker");
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
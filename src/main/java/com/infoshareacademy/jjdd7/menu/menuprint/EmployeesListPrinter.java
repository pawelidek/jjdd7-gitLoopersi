package com.infoshareacademy.jjdd7.menu.menuprint;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.jjdd7.domain.Employee;
import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.function.Function;


public class EmployeesListPrinter implements Menu {

    private EmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public void doAction() {

    /*
    Copyright 2017 freva
    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
    documentation files (the "Software"), to deal in the Software without restriction, including without limitation
    the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
    to permit persons to whom the Software is furnished to do so, subject to the following conditions:
    */
        Character[] borderStyle = AsciiTable.FANCY_ASCII;

        System.out.println(AsciiTable.getTable(borderStyle, employeeRepository.getAllEmployees(), Arrays.asList(
                new Column().header("Index").with(employee -> String.valueOf(employeeRepository.getAllEmployees().indexOf(employee) + 1)),
                new Column().header("Id").with(employee -> String.valueOf(employee.getId())),
                createColumn("Name", Employee::getFirstName),
                createColumn("Last Name", Employee::getSecondName),
                createColumn("Team", employee -> employee.getTeam().toString()),
                createColumn("Work start date", employee -> employee.getStartDate().toString())
        )));


        System.out.println("\n1. Add worker");
        System.out.println("2. Delete worker");
        System.out.println("0. Return");
    }

    private ColumnData<Employee> createColumn(String name, Function<Employee, String> functionReference) {
        return new Column()
                .header(name)
                .headerAlign(HorizontalAlign.CENTER)
                .dataAlign(HorizontalAlign.LEFT)
                .with(functionReference);
    }
}
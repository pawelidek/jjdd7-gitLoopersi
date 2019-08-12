package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.employee.EmployeeServiceImpl;
import com.infoshareacademy.jjdd7.menu.Menu;

public class EmployeesListPrinter implements Menu {

    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @Override
    public void doAction() {
        System.out.println("Employees list [firstName secondName, team, first work start date]: ");

        for (int i = 0; i < employeeServiceImpl.getAllEmployees().size(); i++) {
            System.out.println((i + 1) + ". " + employeeServiceImpl.getAllEmployees().get(i));
        }

        System.out.println("\n1. Add worker");
        System.out.println("2. Delete worker");
        System.out.println("0. Return");
    }
}
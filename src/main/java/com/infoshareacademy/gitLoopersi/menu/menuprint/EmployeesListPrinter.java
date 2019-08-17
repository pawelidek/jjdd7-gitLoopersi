package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.employee.EmployeeService;
import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;

public class EmployeesListPrinter implements Menu {

    @Override
    public void doAction() {
        System.out.println("Employees list [firstName secondName, team, first work start date]: ");
        EmployeeService employeeService = new EmployeeService();
        employeeService.loadEmployeeData();
        for (int i = 0; i < EmployeeRepository.getAllEmployees().size(); i++) {
            System.out.println((i + 1) + ". " + EmployeeRepository.getAllEmployees().get(i));
        }

        System.out.println("\n1. Add worker");
        System.out.println("2. Delete worker");
        System.out.println("0. Return");
        System.out.println("Type \"exit\" to close the app");
    }
}
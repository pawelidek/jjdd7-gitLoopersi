package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.repository.EmployeeRepository;


public class EmployeesListPrinter implements Menu {

    private EmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public void doAction() {

        for (int i = 0; i < employeeRepository.getAllEmployees().size(); i++) {
            System.out.println((i + 1) + ". " + employeeRepository.getAllEmployees().get(i));
        }

        System.out.println("\n1. Add worker");
        System.out.println("2. Delete worker");
        System.out.println("0. Return");
    }
}
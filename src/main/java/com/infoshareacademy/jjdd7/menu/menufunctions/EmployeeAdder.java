package com.infoshareacademy.jjdd7.menu.menufunctions;

import com.infoshareacademy.jjdd7.employee.EmployeeService;
import com.infoshareacademy.jjdd7.employee.EmployeeServiceImpl;
import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.menu.menuprint.EmployeesListPrinter;

import java.util.Scanner;

public class EmployeeAdder implements Menu {
    EmployeeService employeeService = new EmployeeServiceImpl();
    EmployeesListPrinter employeesListPrinter = new EmployeesListPrinter();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void doAction() {
        employeeService.addEmployee(scanner);
    }
}
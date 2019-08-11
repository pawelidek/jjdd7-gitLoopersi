package com.infoshareacademy.jjdd7.menu.menufunctions;

import com.infoshareacademy.jjdd7.employee.EmployeeService;
import com.infoshareacademy.jjdd7.employee.EmployeeServiceImpl;
import com.infoshareacademy.jjdd7.menu.Menu;

import java.util.Scanner;

public class EmployeeDeleter implements Menu {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void doAction() {
        employeeService.deleteEmployee(scanner);
    }
}
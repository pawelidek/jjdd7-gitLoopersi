package com.infoshareacademy.jjdd7;

import com.infoshareacademy.jjdd7.employee.EmployeeService;
import com.infoshareacademy.jjdd7.employee.EmployeeServiceImpl;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee(scanner);
    }
}
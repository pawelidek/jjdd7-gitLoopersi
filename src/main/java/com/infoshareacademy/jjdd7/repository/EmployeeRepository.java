package com.infoshareacademy.jjdd7.repository;

import com.infoshareacademy.jjdd7.deserialization.Deserializator;
import com.infoshareacademy.jjdd7.domain.Employee;

import java.util.List;

public class EmployeeRepository {
    private static List<Employee> allEmployees;
    private static final String fileName = "employees.ser";
    private Deserializator deserializator;

    public EmployeeRepository() {
        deserializator = new Deserializator();
        allEmployees = deserializator.deserialize(new Employee(), fileName);
    }

    public static List<Employee> getAllEmployees() {
        return allEmployees;
    }

    public static void setAllEmployees(List<Employee> allEmployees) {
        EmployeeRepository.allEmployees = allEmployees;
    }

    public static String getFileName() {
        return fileName;
    }
}

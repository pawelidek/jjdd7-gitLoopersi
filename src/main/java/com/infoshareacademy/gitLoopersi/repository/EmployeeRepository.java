package com.infoshareacademy.gitLoopersi.repository;

import com.infoshareacademy.gitLoopersi.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

  private static Long currentId = 0L;

  private static List<Employee> employeeList = new ArrayList<>();

  private EmployeeRepository() {
  }

  public static List<Employee> getEmployeeList() {
    return employeeList;
  }

  public static void setEmployeeList(List<Employee> employeeList) {
    EmployeeRepository.employeeList = employeeList;
  }

  public static Long getCurrentId() {
    return currentId;
  }

  public static void incrementCurrentId() {
    currentId++;
  }

  public static void setCurrentId(Long currentId) {
    EmployeeRepository.currentId = currentId;
  }
}
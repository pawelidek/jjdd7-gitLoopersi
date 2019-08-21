package com.infoshareacademy.gitLoopersi.repository;

import com.infoshareacademy.gitLoopersi.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

  private static Long currentId = 0L;

  private static List<Employee> allEmployees = new ArrayList<>();

  public static List<Employee> getAllEmployees() {
    return allEmployees;
  }

  public static void setAllEmployees(List<Employee> allEmployees) {
    EmployeeRepository.allEmployees = allEmployees;
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
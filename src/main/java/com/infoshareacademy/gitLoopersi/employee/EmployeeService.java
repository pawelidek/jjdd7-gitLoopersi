package com.infoshareacademy.gitLoopersi.employee;

import com.infoshareacademy.gitLoopersi.deserialization.Deserializator;
import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.domain.Team;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;
import com.infoshareacademy.gitLoopersi.serialization.Serializator;
import com.infoshareacademy.gitLoopersi.team.TeamService;

import java.util.List;

public class EmployeeService {

  private Serializator serialization;
  private Deserializator deserializator;
  private static final String fileName = "employees.ser";

  public EmployeeService() {
    serialization = new Serializator();
    deserializator = new Deserializator();
  }

  public void loadEmployeeData() {
    EmployeeRepository.getAllEmployees().clear();
    EmployeeRepository.getAllEmployees()
        .addAll(deserializator.deserialize(new Employee(), fileName));
  }

  public void addTeamIfNotPresent(List<Team> teams) {
    TeamRepository.setAllTeams(teams);
    serialization.serialize(TeamRepository.getAllTeams(), TeamService.getFileName());
  }

  void addEmployee(List<Employee> employeesList) {
    EmployeeRepository.setAllEmployees(employeesList);
    serialization.serialize(EmployeeRepository.getAllEmployees(), getFileName());
    System.out.println("\nProcedure of adding new employee successfully finished.");
    System.out.println("\nType '0' to return or 'Enter' to add another employee.");
  }

  void deleteEmployee(Employee employee) {
    EmployeeRepository.getAllEmployees().remove(employee);
    serialization.serialize(EmployeeRepository.getAllEmployees(), getFileName());
    System.out.println("\nEmployee has been successfully deleted.");
    System.out.println("\nType '0' to return or 'Enter' to delete another employee.");
  }

  private static String getFileName() {
    return fileName;
  }
}
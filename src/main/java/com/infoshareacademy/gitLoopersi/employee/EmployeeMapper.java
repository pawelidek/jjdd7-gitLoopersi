package com.infoshareacademy.gitLoopersi.employee;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.domain.Team;
import com.infoshareacademy.gitLoopersi.properties.AppConfig;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

public class EmployeeMapper {

  public void validateCorrectInputDataForNewEmployee() {

    EmployeeService employeeService = new EmployeeService();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Main menu >> Employees list >> Add an employee");
    System.out.print("\nEnter new employee's first name:\n");
    String firstName = scanner.nextLine();

    while (firstName.length() == 0) {
      System.out.print("\nEmpty value has been put! Enter new employee's first name:\n");
      firstName = scanner.nextLine();
    }
    System.out.print("\nEnter new employee's last name:\n");
    String secondName = scanner.nextLine();
    while (secondName.length() == 0) {
      System.out.println("Empty value has been put! Enter new employee's last name:\n");
      secondName = scanner.nextLine();
    }

    System.out.print("\nEnter new employee's team name:\n");
    String teamName = scanner.nextLine();
    List<Team> allTeams = TeamRepository.getAllTeams();
    Team team = new Team(teamName);
    if (allTeams.contains(team)) {
      team = allTeams.get(allTeams.indexOf(team));
    } else {
      while (teamName.length() == 0 || !allTeams.contains(team)) {
        if (teamName.length() == 0) {
          System.out.println("Empty value has been put! Enter new employee's team:\n");
          String teamNameRepeat = scanner.nextLine();
          team = new Team(teamNameRepeat);
          if (!allTeams.contains(team)) {
            allTeams.add(new Team(teamNameRepeat));
            employeeService.addTeamIfNotPresent(allTeams);
          }
          break;
        } else {
          System.out.print(
              "\nThere is no team named \"" + teamName + "\". Would you like to create it? Y/N:\n");

          String answer = scanner.nextLine().toLowerCase();
          if ("y".equals(answer)) {
            allTeams.add(new Team(teamName));
          } else {
            System.out.println("Employee will be add to team \"unknown\"");
            team = new Team("unknown");
            if (!allTeams.contains(team)) {
              allTeams.add(new Team("unknown"));
            }
          }
          employeeService.addTeamIfNotPresent(allTeams);
          break;
        }
      }
    }
    System.out.print(
        "\nEnter new employee's work start date (Format:" + AppConfig.getDateFormat() + "):\n");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate startWorkDate = null;

    do {
      String startWorkDateString = scanner.nextLine();
      try {
        startWorkDate = simpleDateFormat
            .parse(startWorkDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      } catch (ParseException e) {
        System.out
            .println("Wrong data! Please enter data in format " + AppConfig.getDateFormat() + ":\n");
      }
    } while (startWorkDate == null);

    System.out.println(
        "\nEnter new employee's hirement date (Format: " + AppConfig.getDateFormat() + "):");
    LocalDate startHireDate = null;

    do {
      String startHireDateString = scanner.nextLine();
      try {
        startHireDate = simpleDateFormat
            .parse(startHireDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      } catch (ParseException e) {
        System.out
            .println("Wrong date! Please enter data in format " + AppConfig.getDateFormat() + ":\n");
      }
    } while (startHireDate == null);

    Long id = generateSerialId();

    List<Employee> tempEmployees = EmployeeRepository.getEmployeeList();
    tempEmployees.add(new Employee(id, firstName, secondName, team, startWorkDate, startHireDate));
    employeeService.addEmployee(tempEmployees);

    EmployeeRepository.incrementCurrentId();
  }

  private Long generateSerialId() {
    return EmployeeRepository.getCurrentId() + 1;
  }

  public void validateCorrectInputDataForDeleteEmployee() {

    EmployeeService employeeService = new EmployeeService();

    List<Employee> tempEmployees = EmployeeRepository.getEmployeeList();

    Scanner scanner = new Scanner(System.in);
    boolean isEmployeeFound = false;

    System.out.println("Main menu >> Employees list >> Delete an employee");
    System.out.print("\nEnter Id of employee you would like to delete:\n");

    do {

      String idToCheck = scanner.nextLine();

      while (!isCreatable(idToCheck)) {
        System.out.print("Wrong data! Enter " +
            "Id of employee you would like to delete:\n");
        idToCheck = scanner.nextLine();
      }

      Long id = Long.valueOf(idToCheck);

      for (Employee employee : tempEmployees) {
        if (employee.getId().equals(id)) {
          isEmployeeFound = true;
          employeeService.deleteEmployee(employee);
          break;
        }
      }
      if (!isEmployeeFound) {
        System.out.println("Employee is not found! Enter correct Id:\n");
      }
    } while (!isEmployeeFound);
  }
}
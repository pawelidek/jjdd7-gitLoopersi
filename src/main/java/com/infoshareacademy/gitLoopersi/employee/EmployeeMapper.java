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
    System.out.println("The process of adding an employee.\n");
    System.out.print("Enter new employee's first name: ");
    String firstName = scanner.nextLine();
    while (firstName.length() == 0) {
      System.out.print("\nEmpty value has been put! Enter new employee's first name: ");
      firstName = scanner.nextLine();
    }
    System.out.print("\nEnter new employee's second name: ");
    String secondName = scanner.nextLine();
    while (secondName.length() == 0) {
      System.out.println("Empty value has been put! Enter new employee's second name: ");
      secondName = scanner.nextLine();
    }

    System.out.print("\nEnter new employee's team name: ");
    String teamName = scanner.nextLine();
    List<Team> allTeams = TeamRepository.getAllTeams();
    Team team = new Team(teamName);
    if (allTeams.contains(team)) {
      team = allTeams.get(allTeams.indexOf(team));
    } else {
      while (teamName.length() == 0 || !allTeams.contains(team)) {
        if (teamName.length() == 0) {
          System.out.println("Empty value has been put! Enter new employee's team: ");
          String teamNameRepeat = scanner.nextLine();
          team = new Team(teamNameRepeat);
          if (!allTeams.contains(team)) {
            allTeams.add(new Team(teamNameRepeat));
            employeeService.addTeamIfNotPresent(allTeams);
          }
          break;
        } else {
          System.out.print(
              "\nThere is no team named \"" + teamName + "\". Would you like to create it? Y/N: ");

          String answer = scanner.nextLine().toLowerCase();
          switch (answer) {
            case "y":
              allTeams.add(new Team(teamName));
              break;
            default:
              System.out.println("Employee will be add to team \"unknown\"");
              team = new Team("unknown");
              if (!allTeams.contains(team)) {
                allTeams.add(new Team("unknown"));
              }
              break;
          }
          employeeService.addTeamIfNotPresent(allTeams);
          break;
        }
      }
    }
    System.out.print(
        "\nEnter new employee's start working date (Format:" + AppConfig.getDateFormat() + "): ");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate startWorkDate = null;

    do {
      String startWorkDateString = scanner.nextLine();
      try {
        startWorkDate = simpleDateFormat
            .parse(startWorkDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      } catch (ParseException e) {
        System.out
            .println("Wrong data! Please enter data in format " + AppConfig.getDateFormat() + ": ");
      }
    } while (startWorkDate == null);

    System.out.println(
        "\nEnter new employee's start hire date (Format: " + AppConfig.getDateFormat() + "): ");
    LocalDate startHireDate = null;

    do {
      String startHireDateString = scanner.nextLine();
      try {
        startHireDate = simpleDateFormat
            .parse(startHireDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      } catch (ParseException e) {
        System.out
            .println("Wrong date! Please enter data in format " + AppConfig.getDateFormat() + ": ");
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

    System.out.println("The process of removing an employee");
    System.out.print("\nEnter Id of employee you would like to delete: \n");

    do {

      String idToCheck = scanner.nextLine();

      while (!isCreatable(idToCheck)) {
        System.out.print("Wrong data! Enter " +
            "Id of employee you would like to delete: \n");
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
        System.out.println("Employee is not found! Enter correct Id: ");
      }
    } while (!isEmployeeFound);
  }
}
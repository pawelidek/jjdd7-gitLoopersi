package com.infoshareacademy.gitLoopersi.employee;

import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.domain.Team;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class EmployeeMapper {

    public void validateCorrectInputDataForNewEmployee() {

        EmployeeService employeeService = new EmployeeService();
        EmployeeIdCounter employeeIdCounter = new EmployeeIdCounter();

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

        System.out.print("\nEnter new employee's e-mail address ");
        Pattern emailPattern = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        String emailAddress = null;
        do {
            String emailAddressToCheck = scanner.nextLine();
            Matcher matcher = emailPattern.matcher(emailAddressToCheck);
            if (matcher.matches()) {
                emailAddress = emailAddressToCheck;
            } else {
                System.out.println("Wrong e-mail address! Please enter correct e-mail address: ");
            }
        } while (emailAddress == null);

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
                    System.out.print("\nThere is no team named \"" + teamName + "\". Would you like to create it? Y/N: ");
                    String answer = scanner.nextLine();
                    switch (answer) {
                        case "Y":
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
        System.out.print("\nEnter new employee's start working date (Format: yyyy.MM.dd): ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        LocalDate startWorkDate = null;
        Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]");
        do {
            String startWorkDateString = scanner.nextLine();
            Matcher matcher = datePattern.matcher(startWorkDateString);
            if (matcher.matches()) {
                try {
                    startWorkDate = simpleDateFormat
                            .parse(startWorkDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } catch (ParseException e) {
                    System.out.println("Parse error occurred!");
                }
            } else {
                System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
            }
        } while (startWorkDate == null);

        Long id = Long.valueOf(employeeIdCounter.getIdCounter());
        employeeIdCounter.incrementIdCounter();

        List<Employee> tempEmployees = EmployeeRepository.getAllEmployees();
        tempEmployees.add(new Employee(id, firstName, secondName, team, startWorkDate, emailAddress));
        employeeService.addEmployee(tempEmployees);
    }

    public void validateCorrectInputDataForDeleteEmployee() {

        EmployeeService employeeService = new EmployeeService();

        Scanner scanner = new Scanner(System.in);
        boolean isEmployeeFound = false;
        do {
            System.out.println("The process of removing an employee.\n");
            System.out.print("\nEnter first name of employee you would like to delete: ");
            String firstName = scanner.nextLine();
            while (firstName.length() == 0) {
                System.out.print("\nEmpty value has been put! Enter " +
                        "first name of employee who you would like to delete: ");
                firstName = scanner.nextLine();
            }
            System.out.print("\nEnter second name of employee who you would like to delete: ");
            String secondName = scanner.nextLine();
            while (secondName.length() == 0) {
                System.out.println("Empty value has been put! Enter second " +
                        "name of employee who you would like to delete: ");
                secondName = scanner.nextLine();
            }
            List<Employee> tempEmployees = EmployeeRepository.getAllEmployees();

            for (Employee employee : tempEmployees) {
                if (employee.getFirstName().equals(firstName)) {
                    if (employee.getSecondName().equals(secondName)) {
                        isEmployeeFound = true;
                        employeeService.deleteEmployee(employee);
                        break;
                    }
                }
            }
            if (!isEmployeeFound) {
                System.out.println("Employee is not found! Enter correct data of employee.");
            }
        } while (!isEmployeeFound);
    }
}
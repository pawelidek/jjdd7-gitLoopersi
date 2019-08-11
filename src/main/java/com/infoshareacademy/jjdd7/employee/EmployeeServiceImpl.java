package com.infoshareacademy.jjdd7.employee;

import com.infoshareacademy.jjdd7.deserialization.EmployeeDeserializator;
import com.infoshareacademy.jjdd7.deserialization.EmployeeDeserializatorImpl;
import com.infoshareacademy.jjdd7.domain.Employee;
import com.infoshareacademy.jjdd7.domain.Team;
import com.infoshareacademy.jjdd7.serialization.EmployeeSerializator;
import com.infoshareacademy.jjdd7.serialization.EmployeeSerializatorImpl;
import com.infoshareacademy.jjdd7.serialization.TeamSerializator;
import com.infoshareacademy.jjdd7.serialization.TeamSerializatorImpl;
import com.infoshareacademy.jjdd7.team.TeamServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeSerializator serialization;
    private EmployeeDeserializator deserialization;
    private List<Employee> listOfEmployees;
    private final String fileName = "employees.ser";

    public EmployeeServiceImpl() {
        serialization = new EmployeeSerializatorImpl();
        deserialization = new EmployeeDeserializatorImpl();
        listOfEmployees = deserialization.deserialize(this.fileName);
    }

    @Override
    public void addEmployee(Scanner scanner) {
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
        TeamServiceImpl teamService = new TeamServiceImpl();
        List<Team> allTeams = teamService.getAllTeams();
        Team team = new Team(teamName);
        if (allTeams.contains(team)) {
            Team tempTeam = allTeams.get(allTeams.indexOf(team));
            team = tempTeam;
        }
        while (teamName.length() == 0 || !allTeams.contains(team)) {
            if (teamName.length() == 0) {
                System.out.println("Empty value has been put! Enter new employee's team: ");
                secondName = scanner.nextLine();
            } else {
                System.out.print("\nThere is no team named \"" + teamName + "\". Would you like to create it? Y/N: ");
                String answer = scanner.nextLine();
                switch (answer) {
                    case "Y":
                        allTeams.add(new Team(teamName));
                        break;
                    default:
                        System.out.println("Employee will be add to team \"unknown\"");
                        allTeams.add(new Team("unknown"));
                        break;
                }
                teamService.setAllTeams(allTeams);
                TeamSerializator teamSerializator = new TeamSerializatorImpl();
                teamSerializator.serialize(teamService.getAllTeams(), "employees.ser");
                break;
            }
        }
        System.out.print("\nEnter new employee's start working date (Format: yyyy.MM.dd): ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date startWorkDate = null;
        Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]");
        do {
            String startWorkDateString = scanner.nextLine();
            Matcher matcher = datePattern.matcher(startWorkDateString);
            if (matcher.matches()) {
                try {
                    startWorkDate = simpleDateFormat.parse(startWorkDateString);
                } catch (ParseException e) {
                    System.out.println("Parse error occured!");
                }
            } else {
                System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
            }

        } while (startWorkDate == null);
        System.out.println("Procedure of adding new employee successfully finished.");
        listOfEmployees.add(new Employee(firstName, secondName, team, startWorkDate));
        serialization.serialize(this.listOfEmployees, this.fileName);
    }

    @Override
    public void deleteEmployee(Scanner scanner) {
        Boolean isEmployeeFound = false;
        do {
            System.out.print("\nEnter first name of employee you would like to delete: ");
            String firstName = scanner.nextLine();
            while (firstName.length() == 0) {
                System.out.print("\nEmpty value has been put! Enter first name of employee who you would like to delete: ");
                firstName = scanner.nextLine();
            }
            System.out.print("\nEnter second name of employee who you would like to delete: ");
            String secondName = scanner.nextLine();
            while (secondName.length() == 0) {
                System.out.println("Empty value has been put! Enter second name of employee who you would like to delete: ");
                secondName = scanner.nextLine();
            }
            List<Employee> allEmployees = getAllEmployees();
            for (Employee employee : allEmployees) {
                if (employee.getFirstName().equals(firstName)) {
                    if (employee.getSecondName().equals(secondName)) {
                        allEmployees.remove(employee);
                        isEmployeeFound = true;
                        System.out.println("Employee has been succesfully deleted.");
                        break;
                    }
                }
            }

            if (!isEmployeeFound) {
                System.out.println("Employee is not found! Enter correct data of employee.");
            }
        } while (!isEmployeeFound);
        serialization.serialize(this.listOfEmployees, this.fileName);
    }

    public List<Employee> getAllEmployees() {
        return listOfEmployees;
    }

    public void setAllEmployees(List<Employee> listOfEmployees) {
        this.listOfEmployees = listOfEmployees;
    }
}

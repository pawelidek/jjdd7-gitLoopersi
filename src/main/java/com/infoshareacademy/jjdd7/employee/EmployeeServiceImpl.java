package com.infoshareacademy.jjdd7.employee;

import com.infoshareacademy.jjdd7.deserialization.DeserializationEmployee;
import com.infoshareacademy.jjdd7.deserialization.DeserializationEmployeeImpl;
import com.infoshareacademy.jjdd7.domain.Employee;
import com.infoshareacademy.jjdd7.domain.Team;
import com.infoshareacademy.jjdd7.serialization.SerializationEmployee;
import com.infoshareacademy.jjdd7.serialization.SerializationEmployeeImpl;
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
    private SerializationEmployee serialization;
    private DeserializationEmployee deserialization;
    private List<Employee> listOfEmployees;
    private final String fileName = "employees.ser";

    public EmployeeServiceImpl() {
        serialization = new SerializationEmployeeImpl();
        deserialization = new DeserializationEmployeeImpl();
        listOfEmployees = deserialization.deserialize(this.fileName);
    }

    @Override
    public void addEmployee(Scanner scanner) {
        System.out.print("Enter new employee's first name: ");
        String firstName = scanner.nextLine();
        while (firstName.length() == 0) {
            System.out.print("\nEmpty value has been put, enter new employee's first name: ");
            firstName = scanner.nextLine();
        }
        System.out.print("\nEnter new employee's second name: ");
        String secondName = scanner.nextLine();
        while (secondName.length() == 0) {
            System.out.println("Empty value has been put, enter new employee's second name.");
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
                System.out.println("Empty value has been put, enter new employee's team: ");
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
                break;
            }
        }
        System.out.print("\nEnter new employee's start working date (Format: yyyy.MM.dd)");
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
                    System.out.println("Parse error occured");
                }
            } else {
                System.out.println("Wrong data please enter data in format yyyy.MM.dd: ");
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
            System.out.println("Enter first name of employee who you would like to delete: ");
            String firstName = scanner.nextLine();
            while (firstName.length() == 0) {
                System.out.print("\nEmpty value has been put, enter first name of employee who you would like to delete:  ");
                firstName = scanner.nextLine();
            }
            System.out.print("\nEnter second name of employee who you would like to delete: ");
            String secondName = scanner.nextLine();
            while (secondName.length() == 0) {
                System.out.println("Empty value has been put, enter second name of employee who you would like to delete: ");
                secondName = scanner.nextLine();
            }
            List<Employee> allEmployees = getAllEmployees();
            for (int i = 0; i < allEmployees.size(); i++) {
                if (allEmployees.get(i).getFirstName().equals(firstName)) {
                    if (allEmployees.get(i).getSecondName().equals(secondName)) {
                        allEmployees.remove(i);
                        isEmployeeFound = true;
                        System.out.println("Employee has been succesfully deleted");
                    }
                }
            }
            if (!isEmployeeFound) {
                System.out.println("Employee is not found, enter correct data of employee.");
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

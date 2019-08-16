package com.infoshareacademy.jjdd7.employee;

import com.infoshareacademy.jjdd7.domain.Employee;
import com.infoshareacademy.jjdd7.domain.Team;
import com.infoshareacademy.jjdd7.repository.EmployeeRepository;
import com.infoshareacademy.jjdd7.repository.TeamRepository;
import com.infoshareacademy.jjdd7.serialization.Serializator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class EmployeeService {
    private Serializator serialization;

    public EmployeeService() {
        serialization = new Serializator();
    }

    public void addEmployee() {
        Scanner scanner = new Scanner(System.in);
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

        System.out.print("\nEnter new employee's e-mail adress ");
        Pattern emailPattern = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        String emailAdress = null;
        do {
            String emailAdressToCheck = scanner.nextLine();
            Matcher matcher = emailPattern.matcher(emailAdressToCheck);
            if (matcher.matches()) {
                emailAdress = emailAdressToCheck;
            } else {
                System.out.println("Wrong e-mail adress! Please enter correct e-mail adress ");
            }

        } while (emailAdress == null);

        System.out.print("\nEnter new employee's team name: ");
        String teamName = scanner.nextLine();
        List<Team> allTeams = TeamRepository.getAllTeams();
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
                TeamRepository.setAllTeams(allTeams);
                Serializator teamSerializator = new Serializator();
                teamSerializator.serialize(TeamRepository.getAllTeams(), TeamRepository.getFileName());
                break;
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
                    System.out.println("Parse error occured!");
                }
            } else {
                System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
            }

        } while (startWorkDate == null);
        System.out.println("\nProcedure of adding new employee successfully finished.");
        List<Employee> tempEmployees = EmployeeRepository.getAllEmployees();
        Employee employee = new Employee();
        employee.setId(employee.getId() + 1);
        Long id = employee.getId();
        tempEmployees.add(new Employee(id, firstName, secondName, team, startWorkDate, emailAdress));
        EmployeeRepository.setAllEmployees(tempEmployees);
        serialization.serialize(EmployeeRepository.getAllEmployees(), EmployeeRepository.getFileName());
        System.out.println("\nType '0' to return or 'Enter' to add another employee.");
    }

    public void deleteEmployee() {
        Scanner scanner = new Scanner(System.in);
        Boolean isEmployeeFound = false;
        do {
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
                        tempEmployees.remove(employee);
                        EmployeeRepository.setAllEmployees(tempEmployees);
                        serialization.serialize(EmployeeRepository.getAllEmployees(), EmployeeRepository.getFileName());
                        isEmployeeFound = true;
                        System.out.println("\nEmployee has been successfully deleted.");
                        break;
                    }
                }
            }

            if (!isEmployeeFound) {
                System.out.println("Employee is not found! Enter correct data of employee.");
            }
        } while (!isEmployeeFound);
        System.out.println("\nType '0' to return or 'Enter' to delete another employee.");
    }
}

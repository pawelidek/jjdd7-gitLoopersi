package com.infoshareacademy.gitloopersi.team;


import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitloopersi.domain.Employee;
import com.infoshareacademy.gitloopersi.domain.Vacation;
import com.infoshareacademy.gitloopersi.properties.AppConfig;
import com.infoshareacademy.gitloopersi.repository.EmployeeRepository;
import com.infoshareacademy.gitloopersi.repository.TeamRepository;
import com.infoshareacademy.gitloopersi.repository.VacationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TeamVacationSearcher {

  public void searchTeamVacation() {

    System.out.println("Main menu >> Search engine >> Team vacation\n");

    if (EmployeeRepository.getEmployeeList().size() != 0) {
      List<Employee> employeesFromTeam = searchEmployeesFromTeam();
      LocalDate[] datesRange = dateFiltering();
      showListOfMatchingVacations(datesRange, employeesFromTeam);
    } else {
      System.out.println("There are no employees added to database!");
    }

    System.out.println("\nType '0' to return");
    System.out.println("Type \"exit\" to close the app");
  }

  private List<Employee> searchEmployeesFromTeam() {
    Scanner scanner = new Scanner(System.in);
    boolean isTeamFound = false;
    TeamService teamService1 = new TeamService();
    teamService1.loadTeamData();
    System.out.println("List of all teams " + TeamRepository.getAllTeams());
    System.out.println("\nEnter a team that you want to choose:");
    List<Employee> filteredEmployees = new ArrayList<>();
    do {
      String choosenTeam = scanner.nextLine().toLowerCase();
      for (int i = 0; i < TeamRepository.getAllTeams().size(); i++) {
        if (choosenTeam.equals(TeamRepository.getAllTeams().get(i).getName().toLowerCase())) {
          System.out.println("\nTeam selected: " + choosenTeam);
          filteredEmployees = EmployeeRepository.getEmployeeList().stream()
              .filter(employee -> choosenTeam.equals(employee.getTeam().getName().toLowerCase()))
              .collect(Collectors.toList());
          if ("ASC".equals(AppConfig.getSort())) {
            Collections.sort(filteredEmployees);
          } else {
            Collections.reverse(filteredEmployees);
          }
          isTeamFound = true;
        }
      }
      if (!isTeamFound) {
        System.out.println("Incorrect team name, try again:");
      }
    } while (!isTeamFound);
    return filteredEmployees;
  }

  private LocalDate[] dateFiltering() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(
        "\nEnter start date from wanted range(Format: " + AppConfig.getDateFormat() + "):\n");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate firstDate = null;
    do {
      String startWorkDateString = scanner.nextLine();
      try {
        firstDate = simpleDateFormat
            .parse(startWorkDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      } catch (ParseException e) {
        System.out
            .println(
                "Wrong data! Please enter data in format " + AppConfig.getDateFormat() + ":\n");
        continue;
      }
      if (firstDate.isAfter(LocalDate.now(ZoneId.systemDefault()).plusYears(1))) {
        firstDate = null;
        System.out
            .println("Wrong data! Date later than one year "
                + "from now is not allowed here. Enter new Date: ");
      }
    } while (firstDate == null);

    System.out.println(
        "\nEnter end date from wanted range(Format: " + AppConfig.getDateFormat() + "):");
    LocalDate secondDate = null;
    do {
      String startHireDateString = scanner.nextLine();
      try {
        secondDate = simpleDateFormat
            .parse(startHireDateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      } catch (ParseException e) {
        System.out
            .println(
                "\nWrong date! Please enter data in format " + AppConfig.getDateFormat() + ":\n");
      }
      if (secondDate.isAfter(LocalDate.now(ZoneId.systemDefault()).plusYears(1))) {
        secondDate = null;
        System.out
            .println("\nWrong data! Date later than one year "
                + "from now is not allowed here. Enter new Date: ");
      }
      if (!secondDate.isAfter(firstDate) && !firstDate.isEqual(secondDate)) {
        secondDate = null;
        System.out
            .println("\nWrong data! The end date have to be "
                + "later or equal to start working date. Enter new Date: ");
      }
    } while (secondDate == null);
    return new LocalDate[]{firstDate, secondDate};
  }

  private void showListOfMatchingVacations(LocalDate[] dateRange,
      List<Employee> employeesFromTeam) {
    Set<Long> ids = employeesFromTeam
        .stream()
        .map(e -> e.getId())
        .collect(Collectors.toSet());

    Set<LocalDate> rangeofDates = dateRange[0].datesUntil(dateRange[1].plusDays(1))
        .collect(Collectors.toUnmodifiableSet());

    List<Vacation> allVacation = VacationRepository.getVacationList();

    List<Vacation> selectedVacation = allVacation.stream()
        .filter(e -> !Collections.disjoint(e.getDateFrom().datesUntil(e.getDateTo().plusDays(1))
            .collect(Collectors.toUnmodifiableSet()), rangeofDates))
        .filter(e -> ids.contains(e.getEmployeeId()))
        .collect(Collectors.toList());
    System.out.println("");
    Character[] borderStyle = AsciiTable.FANCY_ASCII;
    System.out.println(
        AsciiTable.getTable(borderStyle, selectedVacation,
            Arrays.asList(
                createColumnVacation("Employee ID",
                    i -> String
                        .valueOf(VacationRepository.getVacationList()
                            .indexOf(i) + 1)),
                createColumnVacation("First name", vacation -> employeesFromTeam.stream()
                    .filter(e -> e.getId().equals(vacation.getEmployeeId())).findFirst().get()
                    .getFirstName()),
                createColumnVacation("Second name", vacation -> employeesFromTeam.stream()
                    .filter(e -> e.getId().equals(vacation.getEmployeeId())).findFirst().get()
                    .getSecondName()),
                createColumnVacation("Vacation start date", vacation -> vacation.getDateFrom()
                    .format(DateTimeFormatter.ofPattern(AppConfig.getDateFormat()))),
                createColumnVacation("Vacation end date", vacation -> vacation.getDateTo()
                    .format(DateTimeFormatter.ofPattern(AppConfig.getDateFormat()))),
                createColumnVacation("Duration",
                    vacation -> String.valueOf(vacation.getCountOfDays()))
            )));
  }

  private ColumnData<Vacation> createColumnVacation(String name,
      Function<Vacation, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }
}

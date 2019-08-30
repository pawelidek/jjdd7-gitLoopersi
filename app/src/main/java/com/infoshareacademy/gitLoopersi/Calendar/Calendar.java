package com.infoshareacademy.gitLoopersi.Calendar;


import com.infoshareacademy.gitLoopersi.domain.Holiday;
import com.infoshareacademy.gitLoopersi.domain.Vacation;
import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.parser.TypeOfHoliday;
import com.infoshareacademy.gitLoopersi.repository.HolidayRepository;
import com.infoshareacademy.gitLoopersi.repository.VacationRepository;
import com.infoshareacademy.gitLoopersi.vacation.VacationService;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calendar {

  public void showCalendar() {

    ConsoleCleaner.cleanConsole();
    System.out.println("Main menu >> Calendar");

    final String ANSI_RED = "\u001B[31m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_CYAN = "\u001B[36m";

    Scanner input = new Scanner(System.in);

    int year = 0;
    boolean correctFormat = false;
    while (!correctFormat) {
      System.out.println("\nEnter a year: ");
      try {
        year = input.nextInt();
        correctFormat = true;
      } catch (InputMismatchException e) {
        input.nextLine();
        System.out.println("Enter the correct format: ");
      }
    }

    Month month2 = Month.JANUARY;
    do {
      month2 = month2.plus(1);
    }
    while (month2.equals(Month.JANUARY));

    int dayOfWeek = 0;

    switch (LocalDate.of(year, 1, 1).getDayOfWeek()) {
      case MONDAY:
        dayOfWeek = 0;
        break;
      case TUESDAY:
        dayOfWeek = 1;
        break;
      case WEDNESDAY:
        dayOfWeek = 2;
        break;
      case THURSDAY:
        dayOfWeek = 3;
        break;
      case FRIDAY:
        dayOfWeek = 4;
        break;
      case SATURDAY:
        dayOfWeek = 5;
        break;
      case SUNDAY:
        dayOfWeek = 6;
        break;
    }

    int day = dayOfWeek;
    int dayCounter = day;
    int numberOfDays = 1;

    String monthx = "";
    for (int month = 1; month <= 12; month++) {

      boolean breakPrinterTime = false;
      if (monthToPrintExists(month)) {
        System.out.println("Would you like to print another 3 months? [y/n]");
        Scanner scanner = new Scanner(System.in);
        Boolean inputValidateResult = false;
        do {
          switch (scanner.nextLine().toLowerCase()) {
            case "y":
              inputValidateResult = true;
              break;
            case "n":
              inputValidateResult = true;
              breakPrinterTime = true;
              break;
            default:
              System.out.println("Wrong input. Please enter [y/n]");
          }
        }
        while (!inputValidateResult);
      }
      if (breakPrinterTime) {
        break;
      }

      switch (month) {
        case 1:
          monthx = "January";
          numberOfDays = 31;
          break;
        case 2:
          monthx = "February";
          if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            numberOfDays = 29;
            break;
          } else {
            numberOfDays = 28;
            break;
          }
        case 3:
          monthx = "March";
          numberOfDays = 31;
          break;
        case 4:
          monthx = "April";
          numberOfDays = 30;
          break;
        case 5:
          monthx = "May";
          numberOfDays = 31;
          break;
        case 6:
          monthx = "June";
          numberOfDays = 30;
          break;
        case 7:
          monthx = "July";
          numberOfDays = 31;
          break;
        case 8:
          monthx = "August";
          numberOfDays = 31;
          break;
        case 9:
          monthx = "September";
          numberOfDays = 30;
          break;
        case 10:
          monthx = "October";
          numberOfDays = 31;
          break;
        case 11:
          monthx = "November";
          numberOfDays = 30;
          break;
        case 12:
          monthx = "December";
          numberOfDays = 31;
          break;
      }

      System.out.printf("%15s %d  \n", monthx, year);
      System.out.println("----------------------------");
      System.out.printf("%s %s %s %s %s %s %s\n ", "Mon", "Tue", "Wed", "Thu", "Fri",
          ANSI_YELLOW + "Sat" + ANSI_RESET,
          ANSI_RED + "Sun" + ANSI_RESET);

      for (int space = 1; space <= day; space++) {
        System.out.printf("%4s", "    ");
      }
      for (int i = 1; i <= numberOfDays; i++) {
        dayCounter++;

        LocalDate localDate = LocalDate.of(year, month, i);

        boolean isWorkerHoliday = false;

        new VacationService().loadVacationData();
        List<Vacation> vacationList = VacationRepository.getVacationList();
        List<LocalDate> tempListofWorkerHoliday = new ArrayList<>();
        for (Vacation vacation : vacationList) {
          for (int y = 0; y < vacation.getCountOfDays(); y++) {
            tempListofWorkerHoliday.add(vacation.getDateFrom().plusDays(y));
          }
        }

        for (LocalDate worker : tempListofWorkerHoliday) {
          if (localDate.equals(worker)) {
            isWorkerHoliday = true;
            break;
          }
        }

//        Parser com.infoshareacademy.gitLoopersi.domain.parser = new Parser();
        List<Holiday> myList = HolidayRepository.getAllHolidays();
        boolean isHoliday = false;

        for (Holiday holiday : myList) {
          if (holiday.getTypeOfHoliday() != TypeOfHoliday.NATIONAL_HOLIDAY) {
            continue;
          }
          if (localDate.equals(holiday.getDate())) {
            isHoliday = true;
            break;
          }
        }

        if (dayCounter % 7 == 0) {
          System.out.printf(ANSI_RED + "%-4d" + ANSI_RESET + "\n ", i);
        } else if ((dayCounter + 1) % 7 == 0) {
          System.out.printf(ANSI_YELLOW + "%-4d" + ANSI_RESET, i);
        } else if (isHoliday) {
          System.out.printf(ANSI_RED + "%-4d" + ANSI_RESET, i);
        } else if (isWorkerHoliday) {
          System.out.printf(ANSI_CYAN + "%-4d" + ANSI_RESET, i);
        } else {
          System.out.printf("%-4d", i);
        }
      }

      day = (day + numberOfDays) % 7;
      System.out.println();

    }

    System.out.println("\nType '0' to return");
    System.out.println("Type \"exit\" to close the app");
  }

  private boolean monthToPrintExists(int month) {
    return (month - 1) % 3 == 0 && month != 0;
  }
}
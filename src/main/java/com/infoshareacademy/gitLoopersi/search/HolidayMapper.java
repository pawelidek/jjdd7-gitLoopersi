package com.infoshareacademy.gitLoopersi.search;

import com.infoshareacademy.gitLoopersi.domain.Holiday;
import com.infoshareacademy.gitLoopersi.parser.Parser;
import com.infoshareacademy.gitLoopersi.parser.TypeOfHoliday;
import com.infoshareacademy.gitLoopersi.repository.HolidayRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class HolidayMapper {

  public void validateCorrectInputDataForHolidayName() {

    HolidayService holidayService = new HolidayService();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Holidays search by name.\n");
    System.out.println("Enter the name of the holiday: ");
    Parser parser = new Parser();
    String name = scanner.nextLine();

    List<Holiday> myList = HolidayRepository.getAllHolidays();
    boolean holidayFound = false;
    for (Holiday holiday : myList) {
      if (name.length() >= 3 && holiday.getName().toLowerCase().contains(name.toLowerCase())) {
        holidayService.searchHolidayByName(holiday.getDate(), holiday.getName());
        holidayFound = true;
      }
    }
    if (!holidayFound) {
      System.out.println("There is no holiday");
    }
    System.out.println("\nType '0' to return or 'Enter' to find another holiday.");
  }

  public void validateCorrectInputDataForHolidayDate() {

    System.out.println("Check whether the given day is a non-working.\n");
    System.out.println("Enter Date in format yyyy.MM.dd :");
    Scanner scanner = new Scanner(System.in);

    DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    LocalDate dateGiven = null;
    Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]");
    Matcher matcher = null;
    do {
      String date = scanner.nextLine();
      matcher = datePattern.matcher(date);
      if (matcher.matches()) {
        try {
          dateGiven = LocalDate.parse(date, dateFormater);
        } catch (DateTimeParseException e) {
          System.out.println("Parse error occured");
        }
      } else {
        System.out.println("Wrong data please enter data in format yyyy.MM.dd: ");
      }
    } while (!matcher.matches());
    Parser parser = new Parser();
    List<Holiday> myList = HolidayRepository.getAllHolidays();
    boolean holidayFound = false;
    for (Holiday holiday : myList) {
      if (holiday.getTypeOfHoliday() != TypeOfHoliday.NATIONAL_HOLIDAY) {
        continue;
      }
      if (dateGiven.equals(holiday.getDate())) {
        System.out.println("Non-working day because it is Holiday");
        holidayFound = true;
        break;
      }
    }
    if (!holidayFound) {
      switch (dateGiven.getDayOfWeek()) {
        case SUNDAY:
          System.out.println("Non-working day because it is Sunday");
          break;
        case SATURDAY:
          System.out.println("Non-working day because it is Saturday");
          break;
        default:
          System.out.println("Working day");
      }
    }
    System.out.println("\nType '0' to return or 'Enter' to check another date.");
  }
}
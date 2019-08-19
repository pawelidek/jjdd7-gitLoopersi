package com.infoshareacademy.gitLoopersi.vacation;

import static java.util.regex.Pattern.compile;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacationMapper {

  public void validateDataForDefineVacation() {

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd");
    LocalDate vacationDateFrom = null;
    Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]");
    System.out.println("Enter vacation date from (Format: yyyy.MM.dd): ");

    LocalDate localDate = LocalDate.now();

    Timestamp timestamp = Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT));
    Long time = timestamp.getTime();

    System.out.println(time);
    System.out.println(localDate);
    System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    do {
      String vacationDateFromString = scanner.nextLine();
      Matcher matcher = datePattern.matcher(vacationDateFromString);
      if (matcher.matches()) {
        try {
          vacationDateFrom = formatDate
              .parse(vacationDateFromString)
              .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
          System.out.println("Parse error occurred!");
        }
      } else {
        System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
      }
    } while (vacationDateFrom == null);

    LocalDate vacationDateTo = null;
    System.out.println("Enter vacation date to (Format: yyyy.MM.dd): ");

    do {
      String vacationDateToString = scanner.nextLine();
      Matcher matcher = datePattern.matcher(vacationDateToString);
      if (matcher.matches()) {
        try {
          vacationDateTo = formatDate
              .parse(vacationDateToString)
              .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
          System.out.println("Parse error occurred!");
        }
      } else {
        System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
      }
    } while (vacationDateTo == null);
  }
}

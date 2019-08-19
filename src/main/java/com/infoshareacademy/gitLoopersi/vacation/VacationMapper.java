package com.infoshareacademy.gitLoopersi.vacation;

import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.matches;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacationMapper {

    private Long vacationDateFromCounter;
    private Long vacationDateToCounter;
    private String vacationDateFromString;
    private String vacationDateToString;
    private LocalDate localDateVacationFrom;


    public void validateDataForDefineVacation() {

        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        LocalDate vacationDateFrom = null;
        LocalDate vacationDateTo = null;

        System.out.println("Enter vacation date from (Format: yyyy.MM.dd): ");
        Pattern pattern = compile("[1-2][0,1,9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]");
        LocalDate localDate = LocalDate.now();
        Timestamp timestampToday = Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT));
        Long todayDate = timestampToday.getTime();

        boolean vacationFromFlag = false;
        boolean vacationToFlag = false;

        do {

            setVacationDateFromString(scanner.nextLine());

            while (!pattern.matcher(getVacationDateFromString()).matches()) {
                System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
                setVacationDateFromString(scanner.nextLine());


            }

            LocalDate localDateVacationFrom = LocalDate.parse(getVacationDateFromString(), dateTimeFormatter);
            Timestamp timestampVacationFrom = Timestamp.valueOf(localDateVacationFrom.atTime(LocalTime.MIDNIGHT));
            setVacationDateFromCounter(timestampVacationFrom.getTime());

            do {
                if (pattern.matcher(getVacationDateFromString()).matches() && todayDate > getVacationDateFromCounter()) {
                vacationDateFrom = LocalDate.parse(getVacationDateFromString(), dateTimeFormatter);

                System.out.println("Wrong data! Give the date from the future: ");

                    localDateVacationFrom = LocalDate.parse(getVacationDateFromString(), dateTimeFormatter);
                    timestampVacationFrom = Timestamp.valueOf(localDateVacationFrom.atTime(LocalTime.MIDNIGHT));
                    setVacationDateFromCounter(timestampVacationFrom.getTime());
                    setVacationDateFromString(scanner.nextLine());
                } else {
                    System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
                    setVacationDateFromString(scanner.nextLine());
                }
            } while (todayDate > getVacationDateFromCounter());

            System.out.println("Enter vacation date to (Format: yyyy.MM.dd): ");


            setVacationDateToString(scanner.nextLine());
            while (!pattern.matcher(getVacationDateToString()).matches()) {
                System.out.println("Wrong data! Please enter data in format yyyy.MM.dd: ");
                setVacationDateToString(scanner.nextLine());
            }
            LocalDate localDateVacationTo = LocalDate.parse(getVacationDateToString(), dateTimeFormatter);
            Timestamp timestampVacationTo = Timestamp.valueOf(localDateVacationTo.atTime(LocalTime.MIDNIGHT));
            setVacationDateToCounter(timestampVacationTo.getTime());

            while (todayDate > getVacationDateToCounter() && getVacationDateToCounter() > getVacationDateFromCounter()) {
                vacationDateTo = LocalDate.parse(getVacationDateToString(), dateTimeFormatter);

                System.out.println("Wrong data! Give the date from the future: ");
                setVacationDateToString(scanner.nextLine());
                localDateVacationTo = LocalDate.parse(getVacationDateToString(), dateTimeFormatter);
                timestampVacationTo = Timestamp.valueOf(localDateVacationTo.atTime(LocalTime.MIDNIGHT));
                setVacationDateToCounter(timestampVacationTo.getTime());
            }
        } while (vacationDateTo == null && vacationDateFrom == null);
    }

    public Long getVacationDateFromCounter() {
        return vacationDateFromCounter;
    }

    public void setVacationDateFromCounter(Long vacationDateFromCounter) {
        this.vacationDateFromCounter = vacationDateFromCounter;
    }

    public Long getVacationDateToCounter() {
        return vacationDateToCounter;
    }

    public void setVacationDateToCounter(Long vacationDateToCounter) {
        this.vacationDateToCounter = vacationDateToCounter;
    }

    public String getVacationDateFromString() {
        return vacationDateFromString;
    }

    public void setVacationDateFromString(String vacationDateFromString) {
        this.vacationDateFromString = vacationDateFromString;
    }

    public String getVacationDateToString() {
        return vacationDateToString;
    }

    public void setVacationDateToString(String vacationDateToString) {
        this.vacationDateToString = vacationDateToString;
    }
}

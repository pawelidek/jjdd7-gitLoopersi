package com.infoshareacademy.jjdd7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHoliday {

    public static void main(String[] args) {


        System.out.println("Enter the name of the holiday: ");
        Scanner scanner = new Scanner(System.in);
        searchHolidayByName(scanner);


    }

    public static void searchHolidayByName(Scanner scanner) {
        ParserImpl parser = new ParserImpl("HolidaysApi.json");
        String name = scanner.nextLine();
        List<Holiday> myList = parser.getListOfHolidays();
        for (Holiday holiday : myList) {
            if (name.equals(holiday.getName()) || holiday.getName().contains(name)) {
                System.out.println(holiday.getDate());
            } else {
                System.out.println("There is no holiday.");
                break;
            }
        }
    }
}



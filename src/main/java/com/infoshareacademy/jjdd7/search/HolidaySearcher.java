package com.infoshareacademy.jjdd7.search;

import com.infoshareacademy.jjdd7.parser.Holiday;
import com.infoshareacademy.jjdd7.parser.ParserImpl;

import java.util.List;
import java.util.Scanner;


public class HolidaySearcher {

    public void searchHolidayByName(Scanner scanner) {
        System.out.println("Enter the name of the holiday: ");
        ParserImpl parser = new ParserImpl("HolidaysApi.json");
        String name = scanner.nextLine();
        List<Holiday> myList = parser.getListOfHolidays();
        boolean ifHolidayfound = false;
        for (Holiday holiday : myList) {
            if (name.length() >= 3 && holiday.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(holiday.getDate() + " " + holiday.getName());
                ifHolidayfound = true;
            }
        }
        if (!ifHolidayfound) {
            System.out.println("There is no holiday");
        }
        System.out.println("\nType '0' to return or 'Enter' to find another holiday.");
    }
}
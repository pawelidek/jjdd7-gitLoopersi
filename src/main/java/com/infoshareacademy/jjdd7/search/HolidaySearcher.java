package com.infoshareacademy.jjdd7.search;

import com.infoshareacademy.jjdd7.domain.Holiday;
import com.infoshareacademy.jjdd7.parser.Parser;
import com.infoshareacademy.jjdd7.repository.HolidayRepository;

import java.util.List;
import java.util.Scanner;


public class HolidaySearcher {

    public void searchHolidayByName(Scanner scanner) {
        System.out.println("Enter the name of the holiday: ");
        Parser parser = new Parser();
        String name = scanner.nextLine();

        List<Holiday> myList = HolidayRepository.getAllHolidays();
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
package com.infoshareacademy.jjdd7;

import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;


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
        boolean ifHolidayfound = false;
        for (Holiday holiday : myList) {
            if (name.toLowerCase().equals(holiday.getName()) || holiday.getName().toLowerCase().contains(name) && name.length() <= 3) {
                System.out.println(holiday.getDate() + " " + holiday.getName());
                ifHolidayfound = true;
                break;
            }
        }
        if(!ifHolidayfound){
            System.out.println("There is no holiday");
        }
    }
}




package com.infoshareacademy.jjdd7;


public class App {
    public static void main(String[] args) {
        ParserImpl parser = new ParserImpl("HolidaysApi.json");
        for (Holiday holiday : parser.getListOfHolidays()) {
            System.out.println(holiday);
        }
    }
}
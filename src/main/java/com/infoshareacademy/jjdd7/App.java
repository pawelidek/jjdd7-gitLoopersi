package com.infoshareacademy.jjdd7;

public class App {
    public static void main(String[] args) {
        ParserImpl parser = new ParserImpl("target/classes/HolidaysApi.json");
        for (Holiday holiday : parser.getListofHolidays()) {
            System.out.println(holiday);
        }
    }
}

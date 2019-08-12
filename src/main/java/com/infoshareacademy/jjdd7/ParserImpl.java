package com.infoshareacademy.jjdd7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParserImpl implements Parser {
    private String path;
    private String inputJSONString;
    private JSONObject inputJSONObject;
    private ArrayList<Holiday> listOfHolidays;


    public ParserImpl(String path) {
        this.path=path;
        this.createTextJSON();
        this.createJSONObject();
        this.loadDateToArray();
    }

    public ArrayList<Holiday> getListOfHolidays() {
        return listOfHolidays;
    }

    public void createTextJSON() {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tempLinesList = null;
        try {
            tempLinesList = Files.readAllLines(Paths.get(this.path));
        } catch (IOException e) {
            System.out.println("Problem with IO occured");
        }
        for (String elem : tempLinesList) {
            stringBuilder.append(elem);
        }
        this.inputJSONString = stringBuilder.toString();
    }

    public void createJSONObject() {
        this.inputJSONObject = new JSONObject(inputJSONString);
    }

    public void loadDateToArray() {
        JSONArray arrayOfHolidays = this.inputJSONObject.getJSONObject("response").getJSONArray("holidays");
        listOfHolidays = new ArrayList<Holiday>();
        for (int i = 0; i < arrayOfHolidays.length(); i++) {
            String name = arrayOfHolidays.getJSONObject(i).getString("name");
           LocalDate date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(arrayOfHolidays.getJSONObject(i).getJSONObject("date").getString("iso")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } catch (java.text.ParseException pe) {
                System.out.println("Problem with parsing data occured");
            }
            String description;
            try {
                description = arrayOfHolidays.getJSONObject(i).getString("description");
            } catch (org.json.JSONException JSONEx) {
                description = "null";
            }
            String tempTypeOfHoliday = arrayOfHolidays.getJSONObject(i).getJSONArray("type").getString(0);
            Type typeOfHoliday = null;
            switch (tempTypeOfHoliday) {
                case "Observance":
                    typeOfHoliday = Type.OBSERVANCE;
                    break;
                case "National holiday":
                    typeOfHoliday = Type.NATIONAL_HOLIDAY;
                    break;
                case "Season":
                    typeOfHoliday = Type.SEASON;
                    break;
                default:
                    System.out.println("Problem with Types occured: there is no such type of holiday");

            }
            listOfHolidays.add(new Holiday(name, date, typeOfHoliday, description));
        }
    }


}

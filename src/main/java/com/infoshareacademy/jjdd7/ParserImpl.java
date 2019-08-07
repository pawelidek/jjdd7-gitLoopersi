package com.infoshareacademy.jjdd7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ParserImpl implements Parser {
    private String path;
    private String inputJSONString;
    private JSONObject inputJSONObject;
    private ArrayList<Holiday> listofHolidays;


    public ParserImpl() {
    }

    public ParserImpl(String path) {
        this.path = new File(path).getAbsolutePath();
        this.createTextJSON();
        this.createJSONObject();
        this.loadDatetoArray();
    }

    public ArrayList<Holiday> getListofHolidays() {
        return listofHolidays;
    }

    public void createTextJSON() {
        StringBuilder stringBuilderb = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(this.path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilderb.append(line);
            }
        } catch (java.io.FileNotFoundException fnfe) {
            throw new RuntimeException("File not founded exception");
        } catch (java.io.IOException ioe) {
            throw new RuntimeException("IOE exception");
        }
        this.inputJSONString = stringBuilderb.toString();
    }

    public void createJSONObject() {
        this.inputJSONObject = new JSONObject(inputJSONString);
    }

    public void loadDatetoArray() {
        JSONArray arrayOfHolidays = this.inputJSONObject.getJSONObject("response").getJSONArray("holidays");
        listofHolidays = new ArrayList<Holiday>();
        for (int i = 0; i < arrayOfHolidays.length(); i++) {
            String name = arrayOfHolidays.getJSONObject(i).getString("name");
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(arrayOfHolidays.getJSONObject(i).getJSONObject("date").getString("iso"));
            } catch (java.text.ParseException pe) {
                throw new RuntimeException("Parse Exception");
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
                    System.out.println("There is no such type of holiday");

            }
            listofHolidays.add(new Holiday(name, date, typeOfHoliday, description));
        }
    }


}

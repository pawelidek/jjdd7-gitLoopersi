package com.infoshareacademy.gitLoopersi.properties;

public class AppConfig {
    public static String dateFormat;
    public static String sortType;

    private AppConfig(){}

    public static String getDateFormat(){
        return dateFormat;
    }
    public static String getSort(){
        return sortType;
    }
}

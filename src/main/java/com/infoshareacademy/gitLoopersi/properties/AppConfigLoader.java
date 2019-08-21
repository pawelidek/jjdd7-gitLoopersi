package com.infoshareacademy.gitLoopersi.properties;

import java.io.*;
import java.util.Properties;
import org.w3c.dom.ls.LSOutput;

public class AppConfigLoader {
    private final String FILEPATH ="application.properties";

    public void loadConfig() {
        try (InputStream input = new FileInputStream(FILEPATH)) {
            Properties appProperties = new Properties();
            appProperties.load(input);
            AppConfig.setDateFormat(appProperties.getProperty("date.format"));
            AppConfig.setSortType(appProperties.getProperty("employee.sort"));
        } catch (IOException iOE) {
            AppConfig.setDateFormat("yyyy.MM.dd");
            AppConfig.setSortType("ASC");
            System.out.println("Error with loading properties, default properties have been set");
        }
    }



}

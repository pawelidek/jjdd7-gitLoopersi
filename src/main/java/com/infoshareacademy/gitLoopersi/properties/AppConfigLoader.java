package com.infoshareacademy.gitLoopersi.properties;

import java.io.*;
import java.util.Properties;

public class AppConfigLoader {
    private final String FILEPATH ="application.properties";

    public void loadConfig() {
        try (InputStream input = new FileInputStream(FILEPATH)) {
            Properties appProperties = new Properties();
            appProperties.load(input);
            AppConfig.dateFormat=appProperties.getProperty("date.format");
            AppConfig.sortType=appProperties.getProperty("employee.sort");
        } catch (IOException iOE) {
            System.out.println("Error with loading properties");
        }
    }


}

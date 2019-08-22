package com.infoshareacademy.gitLoopersi.properties;

import java.io.*;
import java.util.Optional;
import java.util.Properties;
import org.w3c.dom.ls.LSOutput;

public class AppConfigLoader {
    private final String FILEPATH ="application.properties";

    public void loadConfig() {
        try (InputStream input = new FileInputStream(FILEPATH)) {
            Properties appProperties = new Properties();
            appProperties.load(input);
            String dateFormat = Optional.ofNullable(appProperties.getProperty("date.format")).orElse("yyyy.MM.dd");
            AppConfig.setDateFormat(dateFormat);
            String sortType = Optional.ofNullable(appProperties.getProperty("employee.sort")).orElse("ASC");
            AppConfig.setSortType(sortType);
        } catch (IOException iOE) {
            System.out.println("Error with loading properties");
        }
    }



}

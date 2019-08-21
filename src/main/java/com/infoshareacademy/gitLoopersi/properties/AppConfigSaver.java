package com.infoshareacademy.gitLoopersi.properties;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class AppConfigSaver {
    private final String FILEPATH ="application.properties";

    public void saveProperty(){
        try (OutputStream output = new FileOutputStream(FILEPATH)){
            Properties appProperties = new Properties();
            appProperties.put("date.format",AppConfig.getDateFormat());
            appProperties.put("type.sort",AppConfig.getSort());
            appProperties.store(output, null);
        } catch (java.io.IOException iOE){
            System.out.println("Openning properties");
        }
    }
}

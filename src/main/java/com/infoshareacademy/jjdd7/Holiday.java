package com.infoshareacademy.jjdd7;

import java.util.Date;


public class Holiday {

    private String name;
    private Date date;
    private String type;
    private Description description;

    public Holiday() {
    }

    public Holiday(String name, Date date, String type, Description description) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.description=description;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

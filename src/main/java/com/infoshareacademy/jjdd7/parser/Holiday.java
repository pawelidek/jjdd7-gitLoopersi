package com.infoshareacademy.jjdd7.parser;

import java.util.Date;


public class Holiday {

    private String name;
    private Date date;
    private Type type;
    private String description;

    public Holiday(String name, Date date, Type type, String description) {
        this.name = name;
        this.date = date;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}

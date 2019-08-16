package com.infoshareacademy.jjdd7.parser;

public enum TypeOfHoliday {
    NATIONAL_HOLIDAY("NATIONAL HOLIDAY"), OBSERVANCE("OBSERVANCE"), SEASON("SEASON");

    private String type;

    TypeOfHoliday(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}

package com.infoshareacademy.jjdd7;

public enum Example {
    NATIONAL_HOLIDAY("Święto państwowe"), OBSERVANCE("Święto religijne");

    private String holiday;

    private Example(String holiday) {
        this.holiday = holiday;
    }

    public String getHoliday() {
        return this.holiday;
    }
}

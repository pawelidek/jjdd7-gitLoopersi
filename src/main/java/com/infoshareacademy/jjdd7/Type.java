package com.infoshareacademy.jjdd7;

public enum Type {
    NATIONAL_HOLIDAY("NATIONAL HOLIDAY"), OBSERVANCE("OBSERVANCE"), SEASON("SEASON");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}

package com.infoshareacademy.gitLoopersi.types;

public enum StatusType {

    REQUESTED("REQUESTED"), ACCEPTED("ACCEPTED"), SEASON("REJECTED");
    private String type;

    StatusType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

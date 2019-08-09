package com.infoshareacademy.jjdd7.teams_support;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Teams implements Serializable {
    private List<Team> listOfTeams = new ArrayList<>();
    private final String fileName = "teams.ser";

    public List<Team> getAllTeams() {
        return listOfTeams;
    }

    public void setAllTeams(List<Team> listOfTeams) {
        this.listOfTeams = listOfTeams;
    }

    public String getFileName() {
        return this.fileName;
    }
}

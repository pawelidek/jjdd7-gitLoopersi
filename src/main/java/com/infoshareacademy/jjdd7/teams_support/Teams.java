package com.infoshareacademy.jjdd7.teams_support;

import java.io.*;
import java.util.ArrayList;

public class Teams implements Serializable {
    private ArrayList<Team> listOfTeams=new ArrayList<>();
    private final String fileName = "teams.ser";

    public ArrayList<Team> getListOfTeams() {
        return listOfTeams;
    }

    public void setListOfTeams(ArrayList<Team> listOfTeams) {
        this.listOfTeams = listOfTeams;
    }

    public String getFileName() {
        return this.fileName;
    }
}

package com.infoshareacademy.jjdd7.repository;

import com.infoshareacademy.jjdd7.deserialization.Deserializator;
import com.infoshareacademy.jjdd7.domain.Team;

import java.util.List;

public class TeamRepository {
    private static List<Team> allTeams;
    private static final String fileName = "teams.ser";
    private Deserializator deserializator;


    public TeamRepository() {
        deserializator = new Deserializator();
        allTeams = deserializator.deserialize(new Team(), fileName);
    }

    public static List<Team> getAllTeams() {
        return allTeams;
    }

    public static void setAllTeams(List<Team> allTeams) {
        TeamRepository.allTeams = allTeams;
    }

    public static String getFileName() {
        return fileName;
    }
}

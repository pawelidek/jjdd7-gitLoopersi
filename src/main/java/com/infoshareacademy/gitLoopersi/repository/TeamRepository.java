package com.infoshareacademy.gitLoopersi.repository;

import com.infoshareacademy.gitLoopersi.domain.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

  private static List<Team> allTeams = new ArrayList<>();

  private TeamRepository() {
  }

  public static List<Team> getAllTeams() {
    return allTeams;
  }

  public static void setAllTeams(List<Team> allTeams) {
    TeamRepository.allTeams = allTeams;
  }
}
package com.infoshareacademy.gitLoopersi.team;

import com.infoshareacademy.gitLoopersi.deserialization.Deserializator;
import com.infoshareacademy.gitLoopersi.domain.Team;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;
import com.infoshareacademy.gitLoopersi.serialization.Serializator;
import java.util.List;

public class TeamService {

  private Serializator serialization;
  private Deserializator deserializator;
  private static final String fileName = "teams.ser";

  public TeamService() {
    serialization = new Serializator();
    deserializator = new Deserializator();
  }

  public void loadTeamData() {
    TeamRepository.getAllTeams().clear();
    TeamRepository.getAllTeams().addAll(deserializator.deserialize(new Team(), fileName));
  }

  void addTeam(Team team) {
    TeamRepository.getAllTeams().add(team);
    serialization.serialize(TeamRepository.getAllTeams(), getFileName());
    System.out.println("\nTeam \"" + team + "\" added to list.");
    System.out.println("\nType '0' to return or 'Enter' to add another team.");
  }

  void deleteTeam(Team team) {
    TeamRepository.getAllTeams().remove(team);
    System.out.println("\nDeleted team: \"" + team + "\"");
    serialization.serialize(TeamRepository.getAllTeams(), getFileName());
    System.out.println("\nType '0' to return or 'Enter' to add another employee.");
  }

  void updateTeamName(List<Team> teams, String newNameOfTeam) {
    TeamRepository.setAllTeams(teams);
    System.out.println("\nTeam name changed to \"" + newNameOfTeam + "\"");
    serialization.serialize(TeamRepository.getAllTeams(), getFileName());
    System.out.println("\nType '0' to return or 'Enter' to delete another team.");
  }

  public static String getFileName() {
    return fileName;
  }
}
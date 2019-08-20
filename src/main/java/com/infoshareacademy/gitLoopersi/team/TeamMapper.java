package com.infoshareacademy.gitLoopersi.team;

import com.infoshareacademy.gitLoopersi.domain.Team;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;

import java.util.List;
import java.util.Scanner;

public class TeamMapper {

  public void validateCorrectInputDataForNewTeam() {

    TeamService teamService = new TeamService();

    Scanner scanner = new Scanner(System.in);
    System.out.println("The process of adding a team.\n");
    System.out.println("Enter new teams name: ");
    String newTeam = scanner.nextLine();
    Team team = new Team(newTeam);
    while (TeamRepository.getAllTeams().contains(team)) {
      System.out.println("There is already a team with that name! Type another one: ");
      newTeam = scanner.nextLine();
      team = new Team(newTeam);
    }
    teamService.addTeam(team);
  }

  public void validateCorrectInputDataForDeleteTeam() {

    TeamService teamService = new TeamService();

    Scanner scanner = new Scanner(System.in);
    System.out.println("The process of removing a team.\n");
    System.out.println("Enter a team that you want to delete: ");
    boolean isTeamFound = false;
    do {
      String teamToDelete = scanner.nextLine();
      Team team = new Team(teamToDelete);
      for (int i = 0; i < TeamRepository.getAllTeams().size(); i++) {
        if (team.equals(TeamRepository.getAllTeams().get(i))) {
          List<Team> tempTeams = TeamRepository.getAllTeams();
          teamService.deleteTeam(tempTeams.remove(i));
          isTeamFound = true;
          break;
        }
      }
      if (!isTeamFound) {
        System.out.println("\nThere is no team named like that. Try again: ");
      }
    } while (!isTeamFound);
  }

  public void validateCorrectInputDataForUpdateTeam() {

    TeamService teamService = new TeamService();

    Scanner scanner = new Scanner(System.in);
    boolean isTeamFound = false;
    System.out.println("Team name update process.\n");
    System.out.println("Enter a team that you want to change name of: ");
    do {
      String teamToUpdateName = scanner.nextLine();
      Team team = new Team(teamToUpdateName);
      for (int i = 0; i < TeamRepository.getAllTeams().size(); i++) {
        if (team.equals(TeamRepository.getAllTeams().get(i))) {
          System.out.println("Enter teams new name: ");
          String newNameOfTeam = scanner.nextLine();
          List<Team> tempTeams = TeamRepository.getAllTeams();
          tempTeams.get(i).setName(newNameOfTeam);
          teamService.updateTeamName(tempTeams, newNameOfTeam);
          isTeamFound = true;
          break;
        }
      }
      if (!isTeamFound) {
        System.out.println("There is no team named like that. Try again: ");
      }
    } while (!isTeamFound);
  }
}
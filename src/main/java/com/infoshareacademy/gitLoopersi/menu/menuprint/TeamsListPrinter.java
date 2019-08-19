package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;
import com.infoshareacademy.gitLoopersi.team.TeamService;

public class TeamsListPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Teams list: ");
    TeamService teamService = new TeamService();
    teamService.loadTeamData();
    for (int i = 0; i < TeamRepository.getAllTeams().size(); i++) {
      System.out.println((i + 1) + ". " + TeamRepository.getAllTeams().get(i));
    }

    System.out.println("\n1. Add team");
    System.out.println("2. Change team name");
    System.out.println("3. Delete team");
    System.out.println("0. Return");
    System.out.println("Type \"exit\" to close the app");
  }
}
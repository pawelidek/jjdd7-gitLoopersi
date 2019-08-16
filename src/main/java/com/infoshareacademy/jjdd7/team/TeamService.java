package com.infoshareacademy.jjdd7.team;

import com.infoshareacademy.jjdd7.domain.Team;
import com.infoshareacademy.jjdd7.repository.TeamRepository;
import com.infoshareacademy.jjdd7.serialization.Serializator;

import java.util.List;
import java.util.Scanner;

public class TeamService {
    private Serializator serialization;

    public TeamService() {
        serialization = new Serializator();
    }

    public void addTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new teams name: ");
        String newTeam = scanner.nextLine();
        Team team = new Team(newTeam);
        while (TeamRepository.getAllTeams().contains(team)) {
            System.out.println("There is already a team with that name! Type another one: ");
            newTeam = scanner.nextLine();
            team = new Team(newTeam);
        }
        List<Team> tempTeams = TeamRepository.getAllTeams();
        tempTeams.add(team);
        TeamRepository.setAllTeams(tempTeams);
        serialization.serialize(TeamRepository.getAllTeams(), TeamRepository.getFileName());
        System.out.println("\nTeam \"" + newTeam + "\" added to list.");
        System.out.println("\nType '0' to return or 'Enter' to add another team.");
    }

    public void updateTeamName() {
        Scanner scanner = new Scanner(System.in);
        Boolean isTeamFound = false;
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
                    TeamRepository.setAllTeams(tempTeams);
                    System.out.println("\nTeam name changed to \"" + newNameOfTeam + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("There is no team named like that. Try again: ");
            }
        } while (!isTeamFound);
        serialization.serialize(TeamRepository.getAllTeams(), TeamRepository.getFileName());
        System.out.println("\nType '0' to return or 'Enter' to delete another team.");
    }

    public void deleteTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a team that you want to delete: ");
        Boolean isTeamFound = false;
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < TeamRepository.getAllTeams().size(); i++) {
                if (team.equals(TeamRepository.getAllTeams().get(i))) {
                    List<Team> tempTeams = TeamRepository.getAllTeams();
                    tempTeams.remove(i);
                    TeamRepository.setAllTeams(tempTeams);
                    System.out.println("Deleted team: \"" + teamToUpdateName + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("\nThere is no team named like that. Try again: ");
            }
        } while (!isTeamFound);
        serialization.serialize(TeamRepository.getAllTeams(), TeamRepository.getFileName());
        System.out.println("\nType '0' to return or 'Enter' to add another employee.");
    }
}

package com.infoshareacademy.jjdd7.teams_support;

import com.infoshareacademy.jjdd7.serialization_deserialization.*;

import java.util.List;
import java.util.Scanner;

public class TeamServiceImpl implements TeamService {
    private Teams teams;
    private Serialization serialization;
    private Deserialization deserialization;

    public TeamServiceImpl() {
        serialization = new SerializationImpl();
        deserialization = new DeserializationImpl();
        teams = deserialization.deserialize();

    }

    @Override
    public void addTeam(Scanner scanner) {
        System.out.println("Wprowadz nazwę nowego zespołu: ");
        String newTeam = scanner.nextLine();
        Team team = new Team(newTeam);
        while (teams.getAllTeams().contains(team)) {
            System.out.println("Nazwa zduplikowana, wprowadz nazwe nowego zespołu: ");
            newTeam = scanner.nextLine();
            team = new Team(newTeam);
        }
        List<Team> tempTeams = teams.getAllTeams();
        tempTeams.add(team);
        teams.setAllTeams(tempTeams);
        System.out.println("Zespół \"" + newTeam + "\" dodany do listy.");
        serialization.serialize(teams);
    }

    @Override
    public void updateTeamName(Scanner scanner) {
        Boolean isTeamFound = false;
        System.out.println("Wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < teams.getAllTeams().size(); i++) {
                if (team.equals(teams.getAllTeams().get(i))) {
                    System.out.println("Wprowadź nową nazwę zespołu: ");
                    String newNameOfTeam = scanner.nextLine();
                    List<Team> tempTeams = teams.getAllTeams();
                    tempTeams.get(i).setName(newNameOfTeam);
                    teams.setAllTeams(tempTeams);
                    System.out.println("Zmieniono nazwę zespołu na \"" + newNameOfTeam + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("Nazwa błędna, wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
            }
        } while (!isTeamFound);
        serialization.serialize(teams);
    }

    @Override
    public void deleteTeam(Scanner scanner) {
        System.out.println("Wprowadź nazwę zespołu który chcesz usunąć: ");
        Boolean isTeamFound = false;
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < teams.getAllTeams().size(); i++) {
                if (team.equals(teams.getAllTeams().get(i))) {
                    List<Team> tempTeams = teams.getAllTeams();
                    tempTeams.remove(i);
                    teams.setAllTeams(tempTeams);
                    System.out.println("Usunięto zespół: \"" + teamToUpdateName + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("Nazwa błędna, wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
            }
        } while (!isTeamFound);
        serialization.serialize(teams);
    }
}

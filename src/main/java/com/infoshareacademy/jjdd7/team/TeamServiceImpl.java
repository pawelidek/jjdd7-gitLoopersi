package com.infoshareacademy.jjdd7.team;

import com.infoshareacademy.jjdd7.deserialization.TeamDeserializator;
import com.infoshareacademy.jjdd7.deserialization.TeamDeserializatorImpl;
import com.infoshareacademy.jjdd7.domain.Team;
import com.infoshareacademy.jjdd7.serialization.TeamSerializator;
import com.infoshareacademy.jjdd7.serialization.TeamSerializatorImpl;

import java.util.List;
import java.util.Scanner;

public class TeamServiceImpl implements TeamService {
    private TeamSerializator serialization;
    private TeamDeserializator deserialization;
    private List<Team> listOfTeams;
    private final String fileName = "teams.ser";

    public TeamServiceImpl() {
        serialization = new TeamSerializatorImpl();
        deserialization = new TeamDeserializatorImpl();
        listOfTeams = deserialization.deserialize(this.fileName);
    }

    @Override
    public void addTeam(Scanner scanner) {
        System.out.println("Enter new teams name: ");
        String newTeam = scanner.nextLine();
        Team team = new Team(newTeam);
        while (getAllTeams().contains(team)) {
            System.out.println("There is already a team with that name! Type another one: ");
            newTeam = scanner.nextLine();
            team = new Team(newTeam);
        }
        List<Team> tempTeams = getAllTeams();
        tempTeams.add(team);
        setAllTeams(tempTeams);
        serialization.serialize(this.listOfTeams, this.fileName);
        System.out.println("\nTeam \"" + newTeam + "\" added to list.");
        serialization.serialize(this.listOfTeams, this.fileName);
        System.out.println("\nType '0' to return or 'Enter' to add another team.");
    }

    @Override
    public void updateTeamName(Scanner scanner) {
        Boolean isTeamFound = false;
        System.out.println("Enter a team that you want to change name of: ");
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < getAllTeams().size(); i++) {
                if (team.equals(getAllTeams().get(i))) {
                    System.out.println("Enter teams new name: ");
                    String newNameOfTeam = scanner.nextLine();
                    List<Team> tempTeams = getAllTeams();
                    tempTeams.get(i).setName(newNameOfTeam);
                    setAllTeams(tempTeams);
                    serialization.serialize(this.listOfTeams, this.fileName);
                    System.out.println("\nTeam name changed to \"" + newNameOfTeam + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("There is no team named like that. Try again: ");
            }
        } while (!isTeamFound);
        serialization.serialize(this.listOfTeams, this.fileName);
        System.out.println("\nType '0' to return or 'Enter' to delete another team.");
    }

    @Override
    public void deleteTeam(Scanner scanner) {
        System.out.println("Enter a team that you want to delete: ");
        Boolean isTeamFound = false;
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < getAllTeams().size(); i++) {
                if (team.equals(getAllTeams().get(i))) {
                    List<Team> tempTeams = getAllTeams();
                    tempTeams.remove(i);
                    setAllTeams(tempTeams);
                    serialization.serialize(this.listOfTeams, this.fileName);
                    System.out.println("Deleted team: \"" + teamToUpdateName + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("\nThere is no team named like that. Try again: ");
            }
        } while (!isTeamFound);
        serialization.serialize(this.listOfTeams, this.fileName);
        System.out.println("\nType '0' to return or 'Enter' to add another employee.");
    }

    public List<Team> getAllTeams() {
        return listOfTeams;
    }

    public void setAllTeams(List<Team> listOfTeams) {
        this.listOfTeams = listOfTeams;
    }
}

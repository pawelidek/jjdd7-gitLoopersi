package com.infoshareacademy.jjdd7.teams_support;

import com.infoshareacademy.jjdd7.serialization_deserialization.SerializationDeserialization;

import java.util.ArrayList;
import java.util.Scanner;

public class TeamsFunctionsImpl implements TeamsFunctions{
    public Teams teams;

    public TeamsFunctionsImpl() {
        teams = SerializationDeserialization.deserialize();
    }

    @Override
    public void addTeam(Scanner scanner) {
        System.out.println("Wprowadz nazwę nowego zespołu: ");
        String newTeam=scanner.nextLine();
        Team team=new Team(newTeam);
        while (teams.getListOfTeams().contains(team)) {
            System.out.println("Nazwa zduplikowana, wprowadz nazwe nowego zespołu: ");
            newTeam=scanner.nextLine();
            team=new Team(newTeam);
        }
        ArrayList<Team> tempTeams = teams.getListOfTeams();
        tempTeams.add(team);
        teams.setListOfTeams(tempTeams);
        System.out.println("Zespół \"" + newTeam +"\" dodany do listy.");
        SerializationDeserialization.serialize(teams);
    }

    @Override
    public void updateTeamName(Scanner scanner) {
        Boolean isTeamFound=false;
        System.out.println("Wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < teams.getListOfTeams().size(); i++) {
                if (team.equals(teams.getListOfTeams().get(i))){
                    System.out.println("Wprowadź nową nazwę zespołu: ");
                    String newNameOfTeam=scanner.nextLine();
                    ArrayList<Team> tempTeams = teams.getListOfTeams();
                    tempTeams.get(i).setName(newNameOfTeam);
                    teams.setListOfTeams(tempTeams);
                    System.out.println("Zmieniono nazwę zespołu na \"" +newNameOfTeam+"\"");
                    isTeamFound=true;
                    break;
                }
            }
            if (!isTeamFound){
                System.out.println("Nazwa błędna, wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
            }
        } while (!isTeamFound);
        SerializationDeserialization.serialize(teams);
    }

    @Override
    public void deleteTeam(Scanner scanner) {
        System.out.println("Wprowadź nazwę zespołu który chcesz usunąć: ");
        Boolean isTeamFound=false;
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < teams.getListOfTeams().size(); i++) {
                if (team.equals(teams.getListOfTeams().get(i))){
                    ArrayList<Team> tempTeams = teams.getListOfTeams();
                    tempTeams.remove(i);
                    teams.setListOfTeams(tempTeams);
                    System.out.println("Usunięto zespół: \"" +teamToUpdateName+"\"");
                    isTeamFound=true;
                    break;
                }
            }
            if (!isTeamFound){
                System.out.println("Nazwa błędna, wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
            }
        } while (!isTeamFound);
        SerializationDeserialization.serialize(teams);
    }
}

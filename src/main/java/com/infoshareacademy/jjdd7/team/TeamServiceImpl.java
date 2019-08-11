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
        System.out.println("Wprowadz nazwę nowego zespołu: ");
        String newTeam = scanner.nextLine();
        Team team = new Team(newTeam);
        while (getAllTeams().contains(team)) {
            System.out.println("Nazwa zduplikowana, wprowadz nazwe nowego zespołu: ");
            newTeam = scanner.nextLine();
            team = new Team(newTeam);
        }
        List<Team> tempTeams = getAllTeams();
        tempTeams.add(team);
        setAllTeams(tempTeams);
        serialization.serialize(this.listOfTeams, this.fileName);
        System.out.println("Zespół \"" + newTeam + "\" dodany do listy.");
        serialization.serialize(this.listOfTeams, this.fileName);
    }

    @Override
    public void updateTeamName(Scanner scanner) {
        Boolean isTeamFound = false;
        System.out.println("Wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
        do {
            String teamToUpdateName = scanner.nextLine();
            Team team = new Team(teamToUpdateName);
            for (int i = 0; i < getAllTeams().size(); i++) {
                if (team.equals(getAllTeams().get(i))) {
                    System.out.println("Wprowadź nową nazwę zespołu: ");
                    String newNameOfTeam = scanner.nextLine();
                    List<Team> tempTeams = getAllTeams();
                    tempTeams.get(i).setName(newNameOfTeam);
                    setAllTeams(tempTeams);
                    serialization.serialize(this.listOfTeams, this.fileName);
                    System.out.println("Zmieniono nazwę zespołu na \"" + newNameOfTeam + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("Nazwa błędna, wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
            }
        } while (!isTeamFound);
        serialization.serialize(this.listOfTeams, this.fileName);
    }

    @Override
    public void deleteTeam(Scanner scanner) {
        System.out.println("Wprowadź nazwę zespołu który chcesz usunąć: ");
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
                    System.out.println("Usunięto zespół: \"" + teamToUpdateName + "\"");
                    isTeamFound = true;
                    break;
                }
            }
            if (!isTeamFound) {
                System.out.println("Nazwa błędna, wprowadź nazwę zespołu któremu chcesz zmienić nazwę: ");
            }
        } while (!isTeamFound);
        serialization.serialize(this.listOfTeams, this.fileName);
    }

    public List<Team> getAllTeams() {
        return listOfTeams;
    }

    public void setAllTeams(List<Team> listOfTeams) {
        this.listOfTeams = listOfTeams;
    }
}

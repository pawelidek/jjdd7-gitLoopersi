package com.infoshareacademy.jjdd7.menu.menufunctions;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.team.TeamService;

import java.util.Scanner;

public class TeamDeleter implements Menu {
    private TeamService teamService = new TeamService();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void doAction() {
        teamService.deleteTeam();
    }
}
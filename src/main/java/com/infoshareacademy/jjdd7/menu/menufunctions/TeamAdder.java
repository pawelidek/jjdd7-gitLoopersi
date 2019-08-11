package com.infoshareacademy.jjdd7.menu.menufunctions;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.team.TeamService;
import com.infoshareacademy.jjdd7.team.TeamServiceImpl;

import java.util.Scanner;

public class TeamAdder implements Menu {
    TeamService teamService = new TeamServiceImpl();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void doAction() {
        teamService.addTeam(scanner);
    }
}
package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.team.TeamServiceImpl;

public class TeamsListPrinter implements Menu {

    private TeamServiceImpl teamServiceImpl = new TeamServiceImpl();

    @Override
    public void doAction() {
        System.out.println("Teams list: ");

        for (int i = 0; i < teamServiceImpl.getAllTeams().size(); i++) {
            System.out.println((i + 1) + ". " + teamServiceImpl.getAllTeams().get(i));
        }

        System.out.println("\n1. Add team");
        System.out.println("2. Change team name");
        System.out.println("3. Delete team");
        System.out.println("0. Return");
    }
}
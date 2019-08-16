package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.repository.TeamRepository;

public class TeamsListPrinter implements Menu {

    private TeamRepository teamRepository = new TeamRepository();

    @Override
    public void doAction() {
        System.out.println("Teams list: ");

        for (int i = 0; i < teamRepository.getAllTeams().size(); i++) {
            System.out.println((i + 1) + ". " + teamRepository.getAllTeams().get(i));
        }

        System.out.println("\n1. Add team");
        System.out.println("2. Change team name");
        System.out.println("3. Delete team");
        System.out.println("0. Return");
    }
}
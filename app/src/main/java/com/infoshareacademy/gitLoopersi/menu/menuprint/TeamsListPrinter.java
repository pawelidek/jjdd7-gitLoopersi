package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitLoopersi.domain.Team;
import com.infoshareacademy.gitLoopersi.menu.Menu;
import com.infoshareacademy.gitLoopersi.repository.TeamRepository;
import java.util.Arrays;
import java.util.function.Function;

public class TeamsListPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Teams list\n");
//      Copyright 2017 freva
//      Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
//      documentation files (the "Software"), to deal in the Software without restriction, including without limitation
//      the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
//      to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    System.out.println(
        AsciiTable.getTable(borderStyle, TeamRepository.getAllTeams(), Arrays.asList(
            createColumn("Index",
                team -> String
                    .valueOf(TeamRepository.getAllTeams()
                        .indexOf(team) + 1)),
            createColumn("Team name", Team::getName))
        ));

    System.out.println("\n1. Add new team");
    System.out.println("2. Change team name");
    System.out.println("3. Delete team");
    System.out.println("0. Return");
    System.out.println("\nType \"exit\" to close the app");
  }

  private ColumnData<Team> createColumn(String name,
      Function<Team, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }
}
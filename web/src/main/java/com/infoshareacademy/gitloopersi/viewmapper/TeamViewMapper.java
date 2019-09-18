package com.infoshareacademy.gitloopersi.viewmapper;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.domain.view.TeamView;
import javax.ejb.Stateless;

@Stateless
public class TeamViewMapper {

  public TeamView mapEntityToView(Team team) {

    TeamView teamView = new TeamView();
    teamView.setId(team.getId());
    teamView.setName(team.getName());

    return teamView;
  }
}

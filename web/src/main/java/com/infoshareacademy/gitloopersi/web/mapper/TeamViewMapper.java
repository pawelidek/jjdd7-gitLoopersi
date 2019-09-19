package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.web.view.TeamView;
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

package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.entity.TeamVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.TeamVacationStatResponse;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class TeamVacationStatMapper {

  @Transactional
  public TeamVacationStatResponse mapTeamVacationStatEntityToApi(
      TeamVacationStat teamVacationStat) {

    TeamVacationStatResponse teamVacationStatResponse = new TeamVacationStatResponse();

    teamVacationStatResponse.setQuantity(teamVacationStat.getQuantity());
    teamVacationStatResponse.setTeamName(teamVacationStat.getTeam().getName());

    return teamVacationStatResponse;
  }
}

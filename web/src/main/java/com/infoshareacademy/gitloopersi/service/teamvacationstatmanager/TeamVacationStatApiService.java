package com.infoshareacademy.gitloopersi.service.teamvacationstatmanager;

import com.infoshareacademy.gitloopersi.dao.TeamVacationStatDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.TeamVacationStat;
import com.infoshareacademy.gitloopersi.domain.jsonapi.TeamVacationStatResponse;
import com.infoshareacademy.gitloopersi.web.mapper.TeamVacationStatMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Stateless
public class TeamVacationStatApiService {

  @Inject
  private TeamVacationStatMapper teamVacationStatMapper;

  @Inject
  private TeamVacationStatDaoBean teamVacationStatDaoBean;

  @Transactional
  public List<TeamVacationStatResponse> getTeamVacationStatJsonObjects() {

    List<TeamVacationStat> teamVacationStats = teamVacationStatDaoBean.getTeamVacations();

    List<TeamVacationStatResponse> teamVacationStatResponses = new ArrayList<>();

    teamVacationStats.forEach(teamVacationStat -> {
          TeamVacationStatResponse teamVacationStatResponse = teamVacationStatMapper
              .mapTeamVacationStatEntityToApi(teamVacationStat);
          teamVacationStatResponses.add(teamVacationStatResponse);
        }
    );

    return teamVacationStatResponses;
  }
}

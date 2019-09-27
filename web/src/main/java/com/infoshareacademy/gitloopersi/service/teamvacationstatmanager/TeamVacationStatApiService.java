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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamVacationStatApiService {

  @Inject
  private TeamVacationStatMapper teamVacationStatMapper;

  @Inject
  private TeamVacationStatDaoBean teamVacationStatDaoBean;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Transactional
  public List<TeamVacationStatResponse> getTeamVacationStatJsonObjects() {

    logger.info("TeamVacationStat list is to be converted to TeamVacationStatResponse");

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

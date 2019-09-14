package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.TeamResponse;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public TeamResponse mapEntityToApi(Team team) {

    TeamResponse teamToJSON = new TeamResponse();
    teamToJSON.setName(team.getName());

    logger.info("TeamResponse has been mapped to API");

    return teamToJSON;
  }
}

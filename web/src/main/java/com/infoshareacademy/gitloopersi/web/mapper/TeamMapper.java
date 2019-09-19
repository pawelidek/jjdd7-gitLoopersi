package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.jsonapi.TeamResponse;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public TeamResponse mapEntityToApi(Team team) {

    TeamResponse teamToJSON = new TeamResponse();
    teamToJSON.setId(team.getId());
    teamToJSON.setName(team.getName());

    logger.info("Team entity has been mapped to response");

    return teamToJSON;
  }
}

package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.Team;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public Team mapEntityToApi(com.infoshareacademy.gitloopersi.domain.entity.Team team) {

    Team teamToJSON = new Team();
    teamToJSON.setName(team.getName());

    logger.info("Team has been mapped to API");

    return teamToJSON;
  }
}

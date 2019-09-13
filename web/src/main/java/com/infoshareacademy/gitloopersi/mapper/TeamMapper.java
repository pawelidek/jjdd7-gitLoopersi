package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.TeamApi;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public TeamApi mapEntityToApi(Team teamEntity) {

    TeamApi teamToJSON = new TeamApi();
    teamToJSON.setName(teamEntity.getName());

    logger.info("Team has been mapped to API");

    return teamToJSON;
  }
}

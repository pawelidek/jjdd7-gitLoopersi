package com.infoshareacademy.gitloopersi.team.validator;

import com.infoshareacademy.gitloopersi.service.TeamService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class TeamValidator {

  @EJB
  private TeamService teamService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public boolean isBlank(String name) {
    return StringUtils.isBlank(name);
  }

  public boolean isAlphanumericSpace(String name) {
    return StringUtils.isAlphanumericSpace(name);
  }

  public boolean alreadyExists(String name) {
    return teamService.getTeamList().stream().anyMatch(team -> team.getName().equals(name));
  }
}
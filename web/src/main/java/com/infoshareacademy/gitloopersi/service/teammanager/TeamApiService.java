package com.infoshareacademy.gitloopersi.service.teammanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.jsonapi.TeamResponse;
import com.infoshareacademy.gitloopersi.web.mapper.TeamMapper;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class TeamApiService {

  @EJB
  private TeamService teamService;

  @EJB
  private TeamMapper teamMapper;

  @Transactional
  public TeamResponse getTeamJsonObjectById(Long id) throws JsonProcessingException {
    return teamMapper.mapEntityToApi(teamService.getTeamById(id));
  }
}
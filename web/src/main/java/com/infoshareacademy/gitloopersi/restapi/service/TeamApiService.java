package com.infoshareacademy.gitloopersi.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.api.TeamResponse;
import com.infoshareacademy.gitloopersi.mapper.TeamMapper;
import com.infoshareacademy.gitloopersi.service.TeamService;
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
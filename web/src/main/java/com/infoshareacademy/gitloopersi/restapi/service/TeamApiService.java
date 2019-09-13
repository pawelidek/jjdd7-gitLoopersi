package com.infoshareacademy.gitloopersi.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
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
  public String getTeamJsonObjectById(Long id) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    Team teamById = teamService.getTeamById(id);

    return objectMapper.writeValueAsString(teamMapper.mapEntityToApi(teamById));
  }
}

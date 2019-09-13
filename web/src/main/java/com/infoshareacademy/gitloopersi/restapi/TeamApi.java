package com.infoshareacademy.gitloopersi.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.mapper.TeamMapper;
import com.infoshareacademy.gitloopersi.service.TeamService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/team")
public class TeamApi {

  @Inject
  TeamService teamService;

  @Inject
  TeamMapper teamMapper;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") Long id) throws JsonProcessingException {
    logger.info(
        "Process of prepare response on request find team identified by id={} has been started",
        id);
    Team teamEntity = teamService.getTeamById(id);
    com.infoshareacademy.gitloopersi.domain.api.TeamApi teamToJSON = teamMapper
        .mapEntityToApi(teamEntity);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = objectMapper.writeValueAsString(teamToJSON);
    return Response.ok()
        .entity(jsonStr)
        .build();
  }
}

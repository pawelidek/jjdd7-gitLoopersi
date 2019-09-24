package com.infoshareacademy.gitloopersi.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.exception.TeamNotEmptyException;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamApiService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.validator.TeamValidator;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin/team")
public class AdminTeamController {

  @EJB
  private UserMessagesService userMessagesService;

  @EJB
  private TeamService teamService;

  @EJB
  private TeamApiService teamApiService;

  @Inject
  private TeamValidator teamValidator;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") Long id) throws JsonProcessingException {
    logger.info(
        "Process of prepare response on request find team identified by id={} has been started",
        id);
    return Response.ok()
        .entity(teamApiService.getTeamJsonObjectById(id))
        .build();
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEmployee(final MultivaluedMap<String, String> formParams,
      @Context HttpServletRequest req) {
    logger.info("Api team persistence: {}", formParams);

    Team team = new Team();

    String name = formParams.getFirst("name").trim();

    team.setName(name);

    if (!teamValidator.isTeamDataValid(req, team)) {

      List<String> errorsListFromValidator = (List<String>) req.getSession()
          .getAttribute("errorsListFromValidator");

      for (String s : errorsListFromValidator) {
        userMessagesService
            .addErrorMessage(req.getSession(), s);
      }

      logger.info("A team \"{}\" has not been added", name);
    }

    if (!teamValidator.isTeamUnique(name)) {

      String message = String.format("Team \"%s\" already exists, enter another team name!", name);

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to add existing team \"{}\"", name);
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {
      teamService.addTeam(team);

      String message = String.format("A team \"%s\" has been added!", name);

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("A team \"{}\" has been added", name);
    }

    return checkForErrors(req);
  }

  @PUT
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
  public Response editEmployee(final MultivaluedMap<String, String> formParams,
      @Context HttpServletRequest req) {
    logger.info("Api team editing: {}", formParams);

    Long id = Long.parseLong(formParams.getFirst("id"));
    Team team = teamService.getTeamById(id);

    String name = formParams.getFirst("name").trim();

    team.setName(name);

    if (!teamValidator.isTeamDataValid(req, team)) {

      List<String> errorsListFromValidator = (List<String>) req.getSession()
          .getAttribute("errorsListFromValidator");

      for (String s : errorsListFromValidator) {
        userMessagesService
            .addErrorMessage(req.getSession(), s);
      }

      logger.info("A team \"{}\" has not been edited", name);
    }

    if (!teamValidator.isTeamUnique(name)) {

      String message = String.format("Team \"%s\" already exists, enter another team name!", name);

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to set existing name to another team \"{}\"", name);
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {

      teamService.editTeam(team);

      String message = String.format("A team \"%s\" has been successfully edited", name);

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("A team \"{}\" has been edited", name);
    }

    return checkForErrors(req);
  }

  private Response checkForErrors(@Context HttpServletRequest req) {
    List<String> sessionErrors = userMessagesService.getErrorMessageList(req.getSession());
    if (sessionErrors != null && !sessionErrors.isEmpty()) {
      List<String> errors = userMessagesService.getErrorMessageList(req.getSession());
      userMessagesService.removeErrorMessages(req);
      return Response.status(HttpServletResponse.SC_BAD_REQUEST)
          .entity(errors).build();
    } else {
      return Response.ok().build();
    }
  }

  @DELETE
  public Response deleteEmployee(@QueryParam("id") Long id) {
    try {
      teamService.deleteTeam(id);
      logger.info("A team with id={} has been deleted!", id);
    } catch (TeamNotEmptyException e) {
      logger.info("A team with id={} contains employees and cannot be deleted!", id);
    }
    return Response.ok().build();
  }

}
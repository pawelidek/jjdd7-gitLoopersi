package com.infoshareacademy.gitloopersi.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.service.employeevacationstatmanager.EmployeeVacationStatApiService;
import com.infoshareacademy.gitloopersi.service.monthvacationstatmanager.MonthVacationStatApiService;
import com.infoshareacademy.gitloopersi.service.statusvacationstatmanager.StatusVacationStatApiService;
import com.infoshareacademy.gitloopersi.service.teamvacationstatmanager.TeamVacationStatApiService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/statistics")
public class AdminStatisticController {

  @Inject
  private EmployeeVacationStatApiService employeeVacationStatApiService;

  @Inject
  private MonthVacationStatApiService monthVacationStatApiService;

  @Inject
  private StatusVacationStatApiService statusVacationStatApiService;

  @Inject
  private TeamVacationStatApiService teamVacationStatApiService;

  @GET
  @Path("/employeevacation")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEmployeeVacationStats() throws JsonProcessingException {
    return Response.ok()
        .entity(employeeVacationStatApiService.getEmployeeVacationStatJsonObjects())
        .build();
  }

  @GET
  @Path("/monthvacation")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMonthVacationStats() throws JsonProcessingException {
    return Response.ok()
        .entity(monthVacationStatApiService.getMonthVacationJsonObjects())
        .build();
  }

  @GET
  @Path("/statusvacation")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStatusVacationStats() throws JsonProcessingException {
    return Response.ok()
        .entity(statusVacationStatApiService.getStatusVacationStatJsonObjects())
        .build();
  }

  @GET
  @Path("/teamvacation")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTeamVacationStats() throws JsonProcessingException {
    return Response.ok()
        .entity(teamVacationStatApiService.getTeamVacationStatJsonObjects())
        .build();
  }
}

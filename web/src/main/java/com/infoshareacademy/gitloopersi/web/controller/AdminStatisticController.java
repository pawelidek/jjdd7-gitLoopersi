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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/employeevacation")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEmployeeVacationStats() throws JsonProcessingException {
    return Response.ok()
        .entity(holidayApiService.getHolidayJsonObjectsByPattern(param))
        .build();
  }
}

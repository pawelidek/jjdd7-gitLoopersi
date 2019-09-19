package com.infoshareacademy.gitloopersi.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeApiService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/employee")
public class EmployeeController {

  @EJB
  EmployeeApiService employeeApiService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") Long id) throws JsonProcessingException {
    logger.info(
        "Process of prepare response on request find employee identified by id={} has been started",
        id);
    return Response.ok()
        .entity(employeeApiService.getEmployeeJsonObjectById(id))
        .build();
  }
}

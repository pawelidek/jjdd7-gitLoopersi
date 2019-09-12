package com.infoshareacademy.gitloopersi.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.mapper.EmployeeMapper;
import com.infoshareacademy.gitloopersi.service.EmployeeService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/employee")
public class EmployeeApi {

  @Inject
  EmployeeService employeeService;

  @Inject
  EmployeeMapper employeeMapper;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @GET
  @Path("/id/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("id") Long id) throws JsonProcessingException {
    logger.info(
        "Process of prepare response on request find employee identified by id={} has been started",
        id);
    Employee employeeEntity = employeeService.getEmployeeById(id);
    com.infoshareacademy.gitloopersi.domain.api.EmployeeApi employeeToJSON = employeeMapper
        .mapEntityToApi(employeeEntity);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = objectMapper.writeValueAsString(employeeToJSON);
    return Response.ok()
        .entity(jsonStr)
        .build();
  }
}

package com.infoshareacademy.gitloopersi.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeApiService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.validator.EmployeeValidator;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin/employee")
public class AdminEmployeeController {

  @EJB
  private UserMessagesService userMessagesService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private TeamService teamService;

  @Inject
  private EmployeeValidator employeeValidator;

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

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEmployee(final MultivaluedMap<String, String> formParams,
      @Context HttpServletRequest req) {
    logger.info("api employee persistence: " + formParams);

    Employee employee = new Employee();

    String teamId = formParams.getFirst("team");
    String name = formParams.getFirst("firstName");
    String secondName = formParams.getFirst("secondName");
    String email = formParams.getFirst("email");
    String startDate = formParams.getFirst("startDate");
    String startHireDate = formParams.getFirst("startHireDate");

    setFields(employee, teamId, name, secondName, email, startDate, startHireDate);

    if (!employeeValidator.isMailUnique(email)) {

      String message = String.format("Email address \"%s\" is already in use!", email);

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to use an existing email \"{}\"", email);
    }

    if (employeeValidator.areDatesParseable(startDate, startHireDate)) {
      if (!employeeValidator
          .isStartHireDateEarlierThanOrEqualToStartDate(startHireDate, startDate)) {
        String message = "First employment date has to be earlier or equal to actual employment date!";

        userMessagesService.addErrorMessage(req.getSession(), message);

        logger.info("Tried to set actual employment date earlier than first employment date");
      }
    }

    if (!employeeValidator.isEmployeeDataValid(req, employee)) {

      logger.info("An employee \"{} {}\" has not been added", name, secondName);
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {
      employeeService.addEmployee(employee, Long.parseLong(teamId));

      String message = String.format("An employee \"%s %s\" has been added!", name, secondName);

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("An employee \"{} {}\" has been added", name, secondName);
    }

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

  private void setFields(Employee employee, String teamId, String name, String secondName,
      String email, String startDate, String startHireDate) {
    employee.setFirstName(name);
    employee.setSecondName(secondName);
    employee.setEmail(email);

    if (teamId != null) {
      employee.setTeam(teamService.getTeamById(Long.valueOf(teamId)));
    } else {
      employee.setTeam(null);
    }

    if (!startDate.equals("")) {
      employee.setStartDate(LocalDate.parse(startDate));
    } else {
      employee.setStartDate(null);
    }

    if (!startHireDate.equals("")) {
      employee.setStartHireDate(LocalDate.parse(startHireDate));
    } else {
      employee.setStartHireDate(null);
    }
  }
}

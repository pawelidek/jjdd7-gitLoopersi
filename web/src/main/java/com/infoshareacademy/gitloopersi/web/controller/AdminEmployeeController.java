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
import java.util.Optional;
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

@Path("/admin/employee")
public class AdminEmployeeController {

  @EJB
  private UserMessagesService userMessagesService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private EmployeeApiService employeeApiService;

  @EJB
  private TeamService teamService;

  @Inject
  private EmployeeValidator employeeValidator;

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
    logger.info("Api employee persistence: {}", formParams);

    Employee employeeToCreate = createEmployee(formParams, req);

    if (!employeeValidator.isEmployeeDataValid(req, employeeToCreate)) {

      List<String> errorsListFromValidator = (List<String>) req.getSession()
          .getAttribute("errorsListFromValidator");

      for (String s : errorsListFromValidator) {
        userMessagesService
            .addErrorMessage(req.getSession(), s);
      }

      logger.info("An employee \"{} {}\" has not been added", employeeToCreate.getFirstName(),
          employeeToCreate.getSecondName());
    }

    if (!employeeValidator.isMailUnique(employeeToCreate.getEmail())) {

      String message = String
          .format("Email address \"%s\" is already in use!", employeeToCreate.getEmail());

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to use an existing email \"{}\"", employeeToCreate.getEmail());
    }

    if (employeeValidator.isDateValid(employeeToCreate.getStartDate())
        && employeeValidator.isDateValid(employeeToCreate.getStartHireDate())) {

      if (!employeeValidator
          .isStartHireDateEarlierThanOrEqualToStartDate(employeeToCreate.getStartHireDate(),
              employeeToCreate.getStartDate())) {
        String message = "First employment date has to be earlier or equal to actual employment date!";

        userMessagesService.addErrorMessage(req.getSession(), message);

        logger.info("Tried to set actual employment date earlier than first employment date");
      }
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {
      employeeService.addEmployee(employeeToCreate, employeeToCreate.getTeam().getId());

      String message = String
          .format("An employee \"%s %s\" has been added!", employeeToCreate.getFirstName(),
              employeeToCreate.getSecondName());

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("An employee \"{} {}\" has been added", employeeToCreate.getFirstName(),
          employeeToCreate.getSecondName());
    }

    return checkForErrors(req);
  }

  @PUT
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
  public Response editEmployee(final MultivaluedMap<String, String> formParams,
      @Context HttpServletRequest req) {
    logger.info("Api employee editing: {}", formParams);

    Employee employeeToEdit = createEmployee(formParams, req);

    Long id = Long.parseLong(formParams.getFirst("id"));
    employeeToEdit.setId(id);

    if (!employeeValidator.isEmployeeDataValid(req, employeeToEdit)) {

      List<String> errorsListFromValidator = (List<String>) req.getSession()
          .getAttribute("errorsListFromValidator");

      for (String s : errorsListFromValidator) {
        userMessagesService
            .addErrorMessage(req.getSession(), s);
      }

      logger.info("An employee \"{} {}\" has not been edited", employeeToEdit.getFirstName(),
          employeeToEdit.getSecondName());
    }

    if (!employeeValidator.isMailUniqueOrCurrentUser(employeeToEdit.getEmail(), id)) {

      String message = String
          .format("Email address \"%s\" is already in use!", employeeToEdit.getEmail());

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to use an existing email \"{}\"", employeeToEdit.getEmail());
    }

    if (employeeValidator.isDateValid(employeeToEdit.getStartDate())
        && employeeValidator.isDateValid(employeeToEdit.getStartHireDate())) {
      if (!employeeValidator
          .isStartHireDateEarlierThanOrEqualToStartDate(employeeToEdit.getStartHireDate(),
              employeeToEdit.getStartDate())) {
        String message = "First employment date has to be earlier or equal to actual employment date!";

        userMessagesService.addErrorMessage(req.getSession(), message);

        logger.info("Tried to set actual employment date earlier than first employment date");
      }
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {
      employeeService.editEmployee(employeeToEdit, employeeToEdit.getTeam().getId());

      String message = String
          .format("An employee \"%s %s\" has been edited!", employeeToEdit.getFirstName(),
              employeeToEdit.getSecondName());

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("An employee \"{} {}\" has been edited", employeeToEdit.getFirstName(),
          employeeToEdit.getSecondName());
    }

    return checkForErrors(req);
  }

  private Employee createEmployee(MultivaluedMap<String, String> formParams,
      @Context HttpServletRequest req) {

    Employee employee = new Employee();

    String name = formParams.getFirst("firstName").trim();
    String secondName = formParams.getFirst("secondName").trim();
    String email = formParams.getFirst("email").trim();
    boolean admin = Optional.ofNullable(formParams.getFirst("admin")).map(Boolean::parseBoolean)
        .orElse(false);

    if (formParams.getFirst("team") != null) {
      Long teamId = Long.valueOf(formParams.getFirst("team"));
      employee.setTeam(teamService.getTeamById(teamId));
    } else {
      employee.setTeam(null);
    }

    if (!formParams.getFirst("startDate").isEmpty()) {
      LocalDate startDate = LocalDate.parse(formParams.getFirst("startDate"));
      employee.setStartDate(startDate);
    } else {
      employee.setStartDate(null);
    }

    if (!formParams.getFirst("startHireDate").isEmpty()) {
      LocalDate startHireDate = LocalDate.parse(formParams.getFirst("startHireDate"));
      employee.setStartHireDate(startHireDate);
    } else {
      employee.setStartHireDate(null);
    }

    if (admin) {
      employee.setAdminPermissions();
    } else {
      employee.unsetAdminPermissions();
    }

    employee.setFirstName(name);
    employee.setSecondName(secondName);
    employee.setEmail(email);

    return employee;
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteEmployee(@QueryParam("id") Long id, @Context HttpServletRequest req) {
    String firstName = employeeService.getEmployeeById(id).getFirstName();
    String secondName = employeeService.getEmployeeById(id).getSecondName();
    employeeService.deleteEmployeeById(id);
    String message = String
        .format("Employee \"%s %s\" has been successfully deleted", firstName, secondName);
    userMessagesService
        .addSuccessMessage(req.getSession(), message);
    List<String> successMessageList = userMessagesService.getSuccessMessageList(req.getSession());
    userMessagesService.removeSuccessMessages(req);
    logger.info("Deleted employee \"{}\"", id);
    return Response.ok().entity(successMessageList).build();
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
}
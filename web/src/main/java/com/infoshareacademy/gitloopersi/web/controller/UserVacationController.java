package com.infoshareacademy.gitloopersi.web.controller;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailVacationService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.VacationType;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user/vacation")
public class UserVacationController {

  @EJB
  private UserMessagesService userMessagesService;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private EmailVacationService emailVacationService;

  private static final String EMPLOYEE_ID = "employeeId";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addVacation(final MultivaluedMap<String, String> formParams,
      @Context HttpServletRequest req) {
    logger.info("api employee persistence: " + formParams);

    Vacation vacation = new Vacation();

    Long employeeId = (Long) req.getSession().getAttribute(EMPLOYEE_ID);
    String dateFrom = formParams.getFirst("dateFrom");
    String dateTo = formParams.getFirst("dateTo");
    String vacationType = formParams.getFirst("vacationType");
    String deputy = formParams.getFirst("deputy");

    List<String> sessionErrors = userMessagesService.getErrorMessageList(req.getSession());
    if (sessionErrors != null && !sessionErrors.isEmpty()) {
      List<String> errors = userMessagesService.getErrorMessageList(req.getSession());
      userMessagesService.removeErrorMessages(req);
      return Response.status(HttpServletResponse.SC_BAD_REQUEST)
          .entity(errors).build();
    } else {



      Employee employee = employeeService.getEmployeeById(employeeId);

      setVacationFields(req, vacation, dateFrom, dateTo, vacationType, deputy);

      vacationDefiningService.addVacation(vacation, employeeId);

      emailVacationService.buildEmailMessage(vacation, employee);

      logger.info("Vacation {} was added", vacation.toString());

      return Response.ok().build();
    }

  }

  private void setVacationFields(HttpServletRequest req, Vacation vacation, String dateFrom,
      String dateTo, String vacationType, String deputy) {

    int numberOfSelectedVacationDays = getNumberOfSelectedVacationDays(req);

    vacation.setDaysCount(numberOfSelectedVacationDays);
    vacation.setDeputy(deputy);

    vacation.setVacationType(VacationType.valueOfType(vacationType));

    if (!dateFrom.equals("")) {
      vacation.setDateFrom(LocalDate.parse(dateFrom));
    } else {
      vacation.setDateFrom(null);
    }

    if (!dateTo.equals("")) {
      vacation.setDateTo(LocalDate.parse(dateTo));
    } else {
      vacation.setDateTo(null);
    }
  }

  private int getNumberOfSelectedVacationDays(HttpServletRequest req) {
    return vacationDefiningService
        .getNumberOfSelectedVacationDays(req.getParameter("dateFrom"),
            req.getParameter("dateTo"));
  }
}
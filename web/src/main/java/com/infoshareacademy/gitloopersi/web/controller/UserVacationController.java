package com.infoshareacademy.gitloopersi.web.controller;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.exception.DatesOverlapException;
import com.infoshareacademy.gitloopersi.exception.VacationOutOfPoolException;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailVacationService;
import com.infoshareacademy.gitloopersi.service.emailmanager.VacationRequestMessageBuilder;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.VacationType;
import com.infoshareacademy.gitloopersi.validator.VacationDefiningValidator;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.MessagingException;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user/vacation")
public class UserVacationController {

  @Inject
  private VacationDefiningValidator vacationDefiningValidator;

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

    if (!isValidDate(dateFrom, dateTo)) {

      String message = "Date is not valid. Please try again!";
      logger.warn("Date is not valid {} - {}", dateFrom, dateTo);
      userMessagesService.addErrorMessage(req.getSession(), message);

    } else if (!isValidDateFromFuture(dateFrom, dateTo)) {

      String message = "Date from or date to is not from future. Please try again!";
      logger.warn("Date from {} or date to {} is not from future", dateFrom, dateTo);
      userMessagesService.addErrorMessage(req.getSession(), message);

    } else if (!isValidDateFromBeforeDateTo(dateFrom, dateTo)) {

      String message = "Date from is not before date to. Please try again!";
      logger.warn("DateFrom {} is not before date to {}", dateFrom, dateTo);
      userMessagesService.addErrorMessage(req.getSession(), message);

    } else if (!isValidTurnOfTheYear(dateFrom, dateTo)) {

      String message = "Vacation cannot be reported at the turn of the year. Please try again!";
      logger.warn("Vacation cannot be reported at the turn of the year {} - {}", dateFrom, dateTo);
      userMessagesService.addErrorMessage(req.getSession(), message);

    } else if (!isValidVacationType(vacationType)) {

      String message = "Vacation type is not valid. Select correct type.";
      logger.warn("Vacation type {} is not valid", vacationType);
      userMessagesService.addErrorMessage(req.getSession(), message);

    } else {
      try {
        if (!isValidVacationRequestByEmployee(dateFrom, dateTo, employeeId, vacationType)) {

          String message = "Vacation request is not valid. Please try again!";
          logger.warn("Vacation request for employeeId: {} is not valid", employeeId);
          userMessagesService.addErrorMessage(req.getSession(), message);
        }
      } catch (VacationOutOfPoolException e) {

        String message = "Number of remaining vacation days is not sufficient. Please try again!";
        logger.warn(e.getMessage());
        userMessagesService.addErrorMessage(req.getSession(), message);

      } catch (DatesOverlapException e) {

        String message = "Dates overlap with vacations already notified. Please try again!";
        logger.warn(e.getMessage());
        userMessagesService.addErrorMessage(req.getSession(), message);

      } catch (IOException e) {
        logger.warn(e.getMessage());
      }
    }

    List<String> sessionErrors = userMessagesService.getErrorMessageList(req.getSession());
    if (sessionErrors != null && !sessionErrors.isEmpty()) {
      List<String> errors = userMessagesService.getErrorMessageList(req.getSession());
      userMessagesService.removeErrorMessages(req);
      return Response.status(HttpServletResponse.SC_BAD_REQUEST)
          .entity(errors).build();
    } else {

      String message = "Vacation request send correctly";
      userMessagesService.addSuccessMessage(req.getSession(), message);

      setVacationFields(vacation, dateFrom, dateTo, vacationType, deputy);

      Employee employee = employeeService.getEmployeeById(employeeId);

      vacationDefiningService.addVacation(vacation, employeeId);

      String subject = String.format("%s %s Requesting Vacation Days", employee.getFirstName(),
          employee.getSecondName());

      List<String> recipients = new ArrayList<>();
      recipients.add("gitLoopersi@gmail.com");

      Map<String, Object> messageParams = new HashMap<>();
      messageParams.put("vacation", vacation);
      messageParams.put("employee", employee);

      try {
        emailVacationService
            .prepareEmailAndSendMessage(messageParams, new VacationRequestMessageBuilder(), subject,
                recipients);
      } catch (IOException | MessagingException e) {
        e.printStackTrace();
      }

      logger.info("Vacation {} was added", vacation.toString());

      return Response.ok().build();
    }

  }

  private void setVacationFields(Vacation vacation, String dateFrom,
      String dateTo, String vacationType, String deputy) {

    int numberOfSelectedVacationDays = getNumberOfSelectedVacationDays(dateFrom, dateTo);

    vacation.setDaysCount(numberOfSelectedVacationDays);
    vacation.setDeputy(deputy);

    vacation.setVacationType(VacationType.valueOfType(vacationType));

    if (!StringUtils.isEmpty(dateFrom)) {
      vacation.setDateFrom(LocalDate.parse(dateFrom));
    } else {
      cleanDateFrom();
    }

    if (!StringUtils.isEmpty(dateTo)) {
      vacation.setDateTo(LocalDate.parse(dateTo));
    } else {
      cleanDateTo();
    }
  }

  private void cleanDateTo() {
    Vacation vacation = new Vacation();
    vacation.setDateTo(null);
  }

  private void cleanDateFrom() {
    Vacation vacation = new Vacation();
    vacation.setDateFrom(null);
  }

  private int getNumberOfSelectedVacationDays(String dateFrom, String dateTo) {
    return vacationDefiningService
        .getNumberOfSelectedVacationDays(dateFrom, dateTo);
  }

  private boolean isValidVacationType(String vacationType) {
    return vacationDefiningValidator.isValidVacationType(vacationType);
  }

  private boolean isValidVacationRequestByEmployee(String dateFrom, String dateTo, Long employeeId,
      String vacationType) throws VacationOutOfPoolException, DatesOverlapException, IOException {

    return vacationDefiningService
        .isValidVacationRequestByEmployee(employeeId, dateFrom, dateTo, vacationType);
  }

  private boolean isValidTurnOfTheYear(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidTurnOfTheYear(dateFrom, dateTo);
  }

  private boolean isValidDateFromBeforeDateTo(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidDateFromBeforeDateTo(dateFrom, dateTo);
  }

  private boolean isValidDateFromFuture(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidDateFromFuture(dateFrom)
        && vacationDefiningValidator.isValidDateToFuture(dateTo);
  }

  private boolean isValidDate(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidDateFrom(dateFrom)
        && vacationDefiningValidator.isValidDateTo(dateTo);
  }
}
package com.infoshareacademy.gitloopersi.web.filter;

import com.infoshareacademy.gitloopersi.exception.DatesOverlapException;
import com.infoshareacademy.gitloopersi.exception.VacationOutOfPoolException;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.validator.VacationDefiningValidator;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "MyVacationFilter",
    urlPatterns = {"/user/vacation/report"}
)
public class MyVacationFilter implements Filter {

  private static final String USER_VACATION = "/user/vacation";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private VacationDefiningValidator vacationDefiningValidator;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private UserMessagesService userMessagesService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession httpSession = httpRequest.getSession();
    String dateFrom = httpRequest.getParameter("dateFrom");
    String dateTo = httpRequest.getParameter("dateTo");
    String vacationType = httpRequest.getParameter("vacationType");
    Long employeeId = (Long) httpSession.getAttribute("employeeId");

    if (isValidDate(dateFrom, dateTo)) {

      logger.info("Date is valid {} - {}", dateFrom, dateTo);

      if (isValidDateFromFuture(dateFrom, dateTo)) {

        logger.info("Date is from the future {} - {}", dateFrom, dateTo);

        if (isValidDateFromBeforeDateTo(dateFrom, dateTo)) {

          logger.info("Date from {} is before date to {}", dateFrom, dateTo);

          if (isValidTurnOfTheYear(dateFrom, dateTo)) {

            try {

              if (isValidVacationType(vacationType)) {

                logger.info("Vacation type {} is valid", vacationType);

                if (isValidVacationRequestByEmployee(dateFrom, dateTo, employeeId, vacationType)) {

                  logger.info("Vacation request for employeeId: {} is valid", employeeId);
                  userMessagesService.addSuccessMessage(httpRequest.getSession(),
                      "Vacation request send correctly");

                  chain.doFilter(request, response);

                } else {
                  logger.warn("Vacation request for employeeId: {} is not valid", employeeId);
                  userMessagesService.addErrorMessage(httpRequest.getSession(),
                      "Vacation request is not valid. Please try again!");
                  httpResponse.sendRedirect(USER_VACATION);
                }

              } else {
                logger.warn("Vacation type {} is not valid", vacationType);
                userMessagesService.addErrorMessage(httpRequest.getSession(),
                    "Vacation type is not valid. Select correct type.");
                httpResponse.sendRedirect(USER_VACATION);
              }

            } catch (VacationOutOfPoolException e) {
              logger.warn(e.getMessage());
              userMessagesService.addErrorMessage(httpRequest.getSession(),
                  "Number of remaining vacation days is not sufficient. Please try again!");
              httpResponse.sendRedirect(USER_VACATION);
            } catch (DatesOverlapException e) {
              logger.warn(e.getMessage());
              userMessagesService.addErrorMessage(httpRequest.getSession(),
                  "Dates overlap with vacations already notified. Please try again!");
              httpResponse.sendRedirect(USER_VACATION);
            }
          } else {
            logger.warn("Vacation cannot be reported at the turn of the year {} - {}", dateFrom,
                dateTo);
            userMessagesService.addErrorMessage(httpRequest.getSession(),
                "Vacation cannot be reported at the turn of the year. Please try again!");
            httpResponse.sendRedirect(USER_VACATION);
          }
        } else {
          logger.warn("DateFrom {} is not before date to {}", dateFrom, dateTo);
          userMessagesService.addErrorMessage(httpRequest.getSession(),
              "Date from is not before date to. Please try again!");
          httpResponse.sendRedirect(USER_VACATION);
        }
      } else {
        logger.warn("Date from {} or date to {} is not from future", dateFrom, dateTo);
        userMessagesService.addErrorMessage(httpRequest.getSession(),
            "Date from or date to is not from future. Please try again!");
        httpResponse.sendRedirect(USER_VACATION);
      }
    } else {
      logger.warn("Date is not valid {} - {}", dateFrom, dateTo);
      userMessagesService
          .addErrorMessage(httpRequest.getSession(), "Date is not valid. Please try again!");
      httpResponse.sendRedirect(USER_VACATION);
    }
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

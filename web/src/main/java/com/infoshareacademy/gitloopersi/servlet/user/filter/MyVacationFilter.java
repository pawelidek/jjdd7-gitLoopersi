package com.infoshareacademy.gitloopersi.servlet.user.filter;

import com.infoshareacademy.gitloopersi.vacation.service.VacationDefiningService;
import com.infoshareacademy.gitloopersi.vacation.validator.VacationDefiningValidator;
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

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private VacationDefiningValidator vacationDefiningValidator;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession httpSession = httpRequest.getSession(true);

    String dateFrom = request.getParameter("dateFrom");
    String dateTo = request.getParameter("dateTo");
    Long employeeId = 1L;

    if (isValidDate(dateFrom, dateTo)) {

      logger.info("Date is valid {} - {}", dateFrom, dateTo);

      if (isValidDateFromFuture(dateFrom, dateTo)) {

        logger.info("Date is from the future {} - {}", dateFrom, dateTo);

        if (isValidDateFromBeforeDateTo(dateFrom, dateTo)) {

          logger.info("Date from {} is before date to {}", dateFrom, dateTo);

          if (isValidTurnOfTheYear(dateFrom, dateTo)) {

            if (vacationDefiningService
                .isValidVacationRequestByEmployee(employeeId, dateFrom, dateTo)) {
              logger.info("Vacation request for employeeId: {} is valid", employeeId);


              chain.doFilter(request, response);
            } else {
              logger.warn("Vacation request for employeeId: {} is not valid", employeeId);
              httpSession.setAttribute("errorMessage", "Vacation request is not valid");
              httpResponse.sendRedirect("/user/vacation");
            }
          } else {
            logger.warn("Vacation cannot be reported at the turn of the year {} - {}", dateFrom,
                dateTo);
            httpSession.setAttribute("errorMessage",
                "Vacation cannot be reported at the turn of the year");
            httpResponse.sendRedirect("/user/vacation");
          }
        } else {
          logger.warn("DateFrom {} is not before date to {}", dateFrom, dateTo);
          httpSession.setAttribute("errorMessage", "DateFrom is not before date to");
          httpResponse.sendRedirect("/user/vacation");
        }
      } else {
        logger.warn("Date from {} or date to {} is not from future", dateFrom, dateTo);
        httpSession.setAttribute("errorMessage", "Date from or date to is not from future");
        httpResponse.sendRedirect("/user/vacation");
      }
    } else {
      logger.warn("Date is not valid {} - {}", dateFrom, dateTo);
      httpSession.setAttribute("errorMessage", "Date is not valid");
      httpResponse.sendRedirect("/user/vacation");
    }
}

  private boolean isValidTurnOfTheYear(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidTurnOfTheYear(dateFrom, dateTo);
  }

  private boolean isValidDateFromBeforeDateTo(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidDateFromBeforeDateTo(dateFrom, dateTo);
  }

  private boolean isValidDateFromFuture(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidDateFromFuture(dateFrom) && vacationDefiningValidator
        .isValidDateToFuture(dateTo);
  }

  private boolean isValidDate(String dateFrom, String dateTo) {
    return vacationDefiningValidator.isValidDateFrom(dateFrom) && vacationDefiningValidator
        .isValidDateTo(dateTo);
  }
}

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "MyVacationFilter",
    urlPatterns = {"/user/vacation/*"}
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

    String dateFrom = request.getParameter("dateFrom");
    String dateTo = request.getParameter("dateTo");
    Long employeeId = 1L;

    if (vacationDefiningValidator.isValidDateFrom(dateFrom) && vacationDefiningValidator
        .isValidDateTo(dateTo)) {

      if (vacationDefiningValidator.isValidDateFromFuture(dateFrom) && vacationDefiningValidator
          .isValidDateToFuture(dateTo)) {

        if (vacationDefiningValidator.isValidDateFromBeforeDateTo(dateFrom, dateTo)) {

          if (vacationDefiningService
              .isValidVacationRequestByEmployee(employeeId, dateFrom, dateTo)) {

          } else {
            //error message
          }
        } else {
          //error message
        }
      } else {
        //error message
      }
    } else {
      //error message
    }

    chain.doFilter(request, response);
  }
}

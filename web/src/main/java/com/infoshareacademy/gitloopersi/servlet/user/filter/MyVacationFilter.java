package com.infoshareacademy.gitloopersi.servlet.user.filter;

import com.infoshareacademy.gitloopersi.vacation.validator.VacationDefiningValidator;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(
    filterName = "MyVacationFilter",
    urlPatterns = {"/user/vacation"}
)
public class MyVacationFilter implements Filter {

  @Inject
  private VacationDefiningValidator vacationDefiningValidator;

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

          if (vacationDefiningValidator.isValidOverlappingOfDates(employeeId, dateFrom, dateTo)) {

            if (vacationDefiningValidator.isValidTurnOfTheYear(dateFrom, dateTo)) {

              int numberOfVacationPool = vacationDefiningValidator
                  .calculateVacationPoolForEmployee(employeeId);

              int numberOfSelectedVacationDays = vacationDefiningValidator
                  .calculateNumberOfSelectedVacationDays(dateFrom, dateTo);

              int numberOfRemainingVacationDays = vacationDefiningValidator
                  .calculateRemainingVacationPool(employeeId, numberOfSelectedVacationDays,
                      numberOfVacationPool);

              if (numberOfRemainingVacationDays > 0) {

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
      } else {
        //error message
      }
    } else {
      //error message
    }
  }
}

package com.infoshareacademy.gitloopersi.web.filter;

import com.infoshareacademy.gitloopersi.service.holidaymanager.HolidayService;
import com.infoshareacademy.gitloopersi.validator.SearchHolidayValidator;
import java.io.IOException;
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
    filterName = "SearchHolidayFilter",
    urlPatterns = {"/search/holiday",
        "/search/holiday/dates",
        "/search/holiday/name"}
)
public class SearchHolidayFilter implements Filter {

  @Inject
  HolidayService holidayService;

  @Inject
  SearchHolidayValidator searchHolidayValidator;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    HttpSession httpSession = httpServletRequest.getSession();

    String servletPath = httpServletRequest.getServletPath();
    if (servletPath.equals("/search/holiday/dates")) {
      String startDate = httpServletRequest.getParameter("start_date");
      String endDate = httpServletRequest.getParameter("end_date");
      logger.info("Dates startDate={} and endDate={} are to be validated", startDate, endDate);
      if (searchHolidayValidator.checkIsDateFormatValid(startDate, endDate)) {
        if (searchHolidayValidator.checkIsEndDateLaterThanStartDate(startDate, endDate)) {
        } else {
          httpSession.setAttribute("errorMessage",
              "Date from is after date to, check your dates!");
          httpServletResponse.sendRedirect("/search/holiday");
        }
      } else {
        httpSession.setAttribute("errorMessage",
            "Either of date or both are in incorrect format!");
        httpServletResponse.sendRedirect("/search/holiday");
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}

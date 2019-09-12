package com.infoshareacademy.gitloopersi.filter;


import com.infoshareacademy.gitloopersi.service.HolidayService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "HolidayValuesFilter",
    urlPatterns = {"/admin/holiday/*"}
)
public class HolidayValuesFilter implements Filter {

  @Inject
  HolidayService holidayService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    if (httpServletRequest.getMethod().equalsIgnoreCase("PUT")) {
      Integer id = Integer.parseInt(httpServletRequest.getParameter("id"));
      if (!holidayService.findHolidayById(id).getCustom()) {
        httpServletResponse.sendRedirect("/home");
        logger.warn("An wrong attempt to application data has occurred!");
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}

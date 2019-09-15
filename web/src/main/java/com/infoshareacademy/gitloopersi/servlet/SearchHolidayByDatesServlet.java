package com.infoshareacademy.gitloopersi.servlet;

import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.service.CalendarService;
import com.infoshareacademy.gitloopersi.service.HolidayService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = {
    "/search/holiday",
    "/search/holiday/dates",
    "/search/holiday/name"
})
public class SearchHolidayByDatesServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  HolidayService holidayService;

  @Inject
  private CalendarService calendarService;

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    logger.info("Request GET method");
    String servletPath = req.getServletPath();

    List<Calendar> dates = calendarService.findAllHolidaysDates();

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("userType", req.getSession().getAttribute("userType"));
    dataModel.put("function", "SearchHoliday");
    dataModel.put("dates", dates);
    if (servletPath.equals("/search/holiday/dates")) {
      String startDate = req.getParameter("start_date");
      String endDate = req.getParameter("end_date");
      List<Holiday> foundHolidays = holidayService.findHolidaysInRange(startDate, endDate);
      dataModel.put("holidays", foundHolidays);
    } else if (servletPath.equals("/search/holiday/name")) {

    }
  }
}

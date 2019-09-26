package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.holidaymanager.HolidayService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SearchHolidayByParametersServlet extends HttpServlet {

  @Inject
  HolidayService holidayService;
  @Inject
  TemplateProvider templateProvider;
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    String servletPath = req.getServletPath();
    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("userType", req.getSession().getAttribute("userType"));
    dataModel.put("function", "SearchHoliday");
    dataModel.put("errorMessage", req.getSession().getAttribute("errorMessage"));
    if (servletPath.equals("/search/holiday/dates")
        && req.getSession().getAttribute("errorMessage") == null) {
      String startDate = req.getParameter("start_date");
      String endDate = req.getParameter("end_date");
      List<Holiday> foundHolidays = holidayService.findHolidaysInRange(startDate, endDate);
      if (foundHolidays.isEmpty()) {
        dataModel.put("errorMessage", "No results, type another range of data to get holidays");
      } else {
        dataModel.put("holidays", foundHolidays);
      }
    } else if (servletPath.equals("/search/holiday/name")) {
      String name = req.getParameter("name");
      Holiday holiday = holidayService.findHolidayByName(name);
      if (holiday != null) {
        List<Holiday> foundHolidays = List.of(holiday);
        dataModel.put("holidays", foundHolidays);
      } else {
        dataModel.put("errorMessage", "No result, type another holiday name");
      }
    }
    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");
    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
    req.getSession().removeAttribute("errorMessage");
  }
}

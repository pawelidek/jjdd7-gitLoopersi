package com.infoshareacademy.gitloopersi.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.HolidayService;
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

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  HolidayService holidayService;

  @Inject
  TemplateProvider templateProvider;

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    String servletPath = req.getServletPath();
    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("userType", req.getSession().getAttribute("userType"));
    dataModel.put("function", "SearchHoliday");
    if (servletPath.equals("/search/holiday/dates")) {
      String startDate = req.getParameter("start_date");
      String endDate = req.getParameter("end_date");
      List<Holiday> foundHolidays = holidayService.findHolidaysInRange(startDate, endDate);
      dataModel.put("holidays", foundHolidays);
    } else if (servletPath.equals("/search/holiday/name")) {
      String name = req.getParameter("name");
      Holiday holiday = holidayService.findHolidayByName(name);
      List<Holiday> foundHolidays = List.of(holiday);
      dataModel.put("holidays", foundHolidays);
    }
    logger.info("Method GET - servlet path {}",servletPath);
    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");
    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}

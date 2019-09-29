package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
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

@WebServlet("/admin/statistics")
public class StatisticsServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    List<Calendar> dates = calendarService.findAllHolidaysDates();

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("dates", dates);
    dataModel.put("userType", req.getSession().getAttribute("userType"));
    dataModel.put("function", "Statistics");

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}

package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
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

@WebServlet("/holiday")
public class HolidaysViewerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private HolidayService holidayService;

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private CalendarService calendarService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    Map<String, Object> dataModel = new HashMap<>();
    List<Holiday> holidays = holidayService.findAllHolidays();
    List<Calendar> dates = calendarService.findAllHolidaysDates();

    dataModel.put("userType", req.getSession().getAttribute("userType"));
    dataModel.put("holidays", holidays);
    dataModel.put("function", "HolidaysViewer");
    dataModel.put("dates", dates);
    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");
    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

}
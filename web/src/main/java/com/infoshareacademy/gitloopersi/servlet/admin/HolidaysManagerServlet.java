package com.infoshareacademy.gitloopersi.servlet.admin;

import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.HolidayService;
import com.infoshareacademy.gitloopersi.types.HolidayType;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

@WebServlet("/admin/holiday")
public class HolidaysManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  HolidayService holidayService;

  @Inject
  TemplateProvider templateProvider;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    logger.info("Request GET method");
    Map<String, Object> dataModel = new HashMap<>();
    List<Holiday> holidays = holidayService.findAllHolidays();
    dataModel.put("userType", req.getSession().getAttribute("userType"));
    dataModel.put("holidays", holidays);
    dataModel.put("function", "HolidaysManager");
    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");
    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      e.getStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    logger.info("Request POST method");
    String holidayName = req.getParameter("holiday_name");
    String holiday_date = req.getParameter("holiday_date");
    LocalDate holidayDate = LocalDate
        .parse(holiday_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    HolidayType holidayType = HolidayType.deserialize(req.getParameter("holiday_type"));
    String holidayDescription = req.getParameter("holiday_description");
    holidayService.addHoliday(holidayName, holidayDate, holidayType, holidayDescription);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
    logger.info("Request PUT method");
    Integer id = Integer.parseInt(req.getParameter("id"));
    String holidayName = req.getParameter("holiday_name");
    String holiday_date = req.getParameter("holiday_date");
    LocalDate holidayDate = LocalDate
        .parse(holiday_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    HolidayType holidayType = HolidayType.deserialize(req.getParameter("holiday_type"));
    String holidayDescription = req.getParameter("holiday_description");
    holidayService.modifyHoliday(id, holidayName, holidayDate, holidayType, holidayDescription);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
    logger.info("Request DELETE method");
    Integer idToDelete = Integer.parseInt(req.getParameter("id"));
    holidayService.removeHoliday(idToDelete);
  }

}

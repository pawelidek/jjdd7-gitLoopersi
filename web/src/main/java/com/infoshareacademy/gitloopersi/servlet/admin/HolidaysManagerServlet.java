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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = {
    "/admin/holiday",
    "/admin/holiday/edit"
})
public class HolidaysManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  HolidayService holidayService;

  @Inject
  TemplateProvider templateProvider;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    holidayService
        .addHoliday("Boze Narodzenie", LocalDate.of(2019, 12, 24), HolidayType.NATIONAL_HOLIDAY,
            "swieta");
    holidayService
        .addHoliday("Wielkanoc", LocalDate.of(2019, 4, 10), HolidayType.NATIONAL_HOLIDAY,
            "swieta");
    holidayService.addHoliday("Swieto wojska polskiego", LocalDate.of(2019, 8, 15),
        HolidayType.NATIONAL_HOLIDAY, "swieta");
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, Object> dataModel = new HashMap<>();
    List<Holiday> holidays = holidayService.findAllHolidays();
    String servletPath = req.getServletPath();
    dataModel.put("userType", "admin");

    if (servletPath.equals("/admin/holiday")) {
      dataModel.put("holidays", holidayService.findAllHolidays());
      dataModel.put("function", "HolidaysManager");
    } else if (servletPath.equals("/admin/holiday/edit")){
      dataModel.put("holidays", holidayService.findAllHolidays());
      dataModel.put("function", "HolidaysManager");
      Integer holidayId=Integer.parseInt(req.getParameter("id"));
      dataModel.put("oneHoliday",holidayService.findHolidayById(holidayId));
      dataModel.put("method","put");
    }
    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      e.getStackTrace();
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
    Integer idToDelete = Integer.parseInt(req.getParameter("id"));
    holidayService.removeHoliday(idToDelete);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String holidayName = req.getParameter("holiday_name");
    String holiday_date = req.getParameter("holiday_date");
    LocalDate holidayDate = LocalDate
        .parse(holiday_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    HolidayType holidayType = HolidayType.valueOf(req.getParameter("holiday_type"));
    String holidayDescription = req.getParameter("holiday_description");
    holidayService.addHoliday(holidayName, holidayDate, holidayType, holidayDescription);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
    Integer idToDelete = Integer.parseInt(req.getParameter("id"));
    String holidayName = req.getParameter("name");
    LocalDate holidayDate = LocalDate.of(1991, 12, 12);
    HolidayType holidayType = HolidayType.valueOf(req.getParameter("holiday_type"));
    String holidayMessage = req.getParameter("message");
    Holiday holiday = new Holiday();
    holiday.setId(idToDelete);
    holiday.setName(holidayName);
    holiday.setDate(holidayDate);
    holiday.setHolidayType(holidayType);
    holiday.setDescription(holidayMessage);
    holidayService.modifyHoliday(holiday);
  }
}

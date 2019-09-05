package com.infoshareacademy.gitloopersi.servlet.admin;

import com.infoshareacademy.gitloopersi.entity.Holiday;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/vacation")
public class VacationManagerServlet extends HttpServlet {

  @Inject
  HolidayService holidayService;

  @Inject
  TemplateProvider templateProvider;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, Object> dataModel = new HashMap<>();
    List<Holiday> holidays= holidayService.findAllHolidays();

    if(holidays != null){
      dataModel.put("holidays",holidayService.findAllHolidays());
      dataModel.put("userType","guest");
    } else {
      dataModel.put("errorMessage", "No holiday has been found");
    }

    PrintWriter printWriter = resp.getWriter();
    Template template = templateProvider.getTemplate(getServletContext(),"home.ftlh");

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      e.getStackTrace();
    }
  }
}
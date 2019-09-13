package com.infoshareacademy.gitloopersi.servlet.user;

import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/vacation")
public class MyVacationServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("userType", "user");
    dataModel.put("function", "VacationDefining");

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String dateFrom = req.getParameter("dateFrom");
    String dateTo = req.getParameter("dateTo");
    Long employeeId = 1L;

  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String dateFrom = req.getParameter("dateFrom");
    String dateTo = req.getParameter("dateTo");
    Long employeeId = 1L;
  }

}
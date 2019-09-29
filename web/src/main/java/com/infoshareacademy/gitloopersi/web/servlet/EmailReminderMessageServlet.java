package com.infoshareacademy.gitloopersi.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailParameterCodingService;
import com.infoshareacademy.gitloopersi.web.view.VacationViewString;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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

@WebServlet("/email-msg-reminder")
public class EmailReminderMessageServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider
        .getTemplate(getServletContext(), "emailMessageReminder.ftlh");

    Map<String, Object> params = EmailParameterCodingService
        .doDecode(req.getParameter("params"));

    String jsonList = (String) params.get("vacations");
    VacationViewString[] vacationViewStrings = new ObjectMapper()
        .readValue(jsonList, VacationViewString[].class);
    List<VacationViewString> vacationViews = Arrays.asList(vacationViewStrings);
    params.replace("vacations", vacationViews);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(params, printWriter);
    } catch (
        TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}

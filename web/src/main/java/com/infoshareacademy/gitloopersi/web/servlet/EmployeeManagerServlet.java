package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.validator.EmployeeValidator;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/employee")
public class EmployeeManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private TeamService teamService;

  @Inject
  private EmployeeValidator employeeValidator;

  @Inject
  private CalendarService calendarService;

  @EJB
  private UserMessagesService userMessagesService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<EmployeeView> employeeViews = employeeService.getEmployeesWithTeamsList();
    List<Team> teamList = teamService.getTeamList();
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    List<String> errorMessages = userMessagesService.getErrorMessageList(req.getSession());
    List<String> successMessages = userMessagesService.getSuccessMessageList(req.getSession());

    dataModel.put("userType", "admin");
    dataModel.put("employees", employeeViews);
    dataModel.put("teams", teamList);
    dataModel.put("function", "EmployeeManager");
    dataModel.put("method", "put");
    dataModel.put("dates", dates);
    dataModel.put("error", errorMessages);
    dataModel.put("success", successMessages);

    userMessagesService.removeErrorMessages(req);
    userMessagesService.removeSuccessMessages(req);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    employeeService.deleteEmployeeById(id);
  }
}
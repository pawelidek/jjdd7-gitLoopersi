package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
  private CalendarService calendarService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<EmployeeView> employeeViews = employeeService.getEmployeesWithTeamsList();
    List<Team> teamList = teamService.getTeamList();
    List<Calendar> dates = calendarService.findAllHolidaysDates();

    dataModel.put("userType", "admin");
    dataModel.put("employees", employeeViews);
    dataModel.put("teams", teamList);
    dataModel.put("function", "EmployeeManager");
    dataModel.put("method", "put");
    dataModel.put("dates", dates);

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

    Employee employee = new Employee();
    String teamId = req.getParameter("team");

    setEmployeeFields(req, employee);

    employeeService.addEmployee(employee, Long.parseLong(teamId));
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Long id = Long.parseLong(req.getParameter("id"));
    Employee employee = employeeService.getEmployeeById(id);
    String teamId = req.getParameter("team");

    setEmployeeFields(req, employee);

    employeeService.editEmployee(employee, Long.parseLong(teamId));
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    employeeService.deleteEmployeeById(id);
  }

  private void setEmployeeFields(HttpServletRequest req, Employee employee) {

    String name = req.getParameter("firstName");
    String secondName = req.getParameter("secondName");
    String email = req.getParameter("email");
    LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
    LocalDate startHireDate = LocalDate.parse(req.getParameter("startHireDate"));

    employee.setFirstName(name);
    employee.setSecondName(secondName);
    employee.setEmail(email);
    employee.setStartDate(startDate);
    employee.setStartHireDate(startHireDate);
  }
}
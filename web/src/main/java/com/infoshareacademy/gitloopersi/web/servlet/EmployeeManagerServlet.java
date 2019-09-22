package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
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
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Employee employee = new Employee();

    String teamId = req.getParameter("team");
    String name = req.getParameter("firstName");
    String secondName = req.getParameter("secondName");
    String email = req.getParameter("email");
    String startDate = req.getParameter("startDate");
    String startHireDate = req.getParameter("startHireDate");

    employee.setFirstName(name);
    employee.setSecondName(secondName);
    employee.setEmail(email);

    if (teamId != null) {
      employee.setTeam(teamService.getTeamById(Long.valueOf(teamId)));
    } else {
      employee.setTeam(null);
    }

    if (!startDate.equals("")) {
      employee.setStartDate(LocalDate.parse(startDate));
    } else {
      employee.setStartDate(null);
    }

    if (!startHireDate.equals("")) {
      employee.setStartHireDate(LocalDate.parse(startHireDate));
    } else {
      employee.setStartHireDate(null);
    }

    if (!employeeValidator.isMailUnique(email)) {

      String message = String.format("Email address \"%s\" is already in use!", email);

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to use an existing email \"{}\"", email);
    }

    if (employeeValidator.areDatesParseable(startDate, startHireDate)) {
      if (!employeeValidator
          .isStartHireDateEarlierThanOrEqualToStartDate(startHireDate, startDate)) {
        String message = "First employment date has to be earlier or equal to actual employment date!";

        userMessagesService.addErrorMessage(req.getSession(), message);

        logger.info("Tried to set actual employment date earlier than first employment date");
      }
    }

    if (!employeeValidator.isEmployeeDataValid(req, employee)) {

      logger.info("An employee \"{} {}\" has not been added", name, secondName);
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {
      employeeService.addEmployee(employee, Long.parseLong(teamId));

      String message = String.format("An employee \"%s %s\" has been added!", name, secondName);

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("An employee \"{} {}\" has been added", name, secondName);
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Long id = Long.parseLong(req.getParameter("id"));
    Employee employee = employeeService.getEmployeeById(id);

    String teamId = req.getParameter("team");
    String name = req.getParameter("firstName");
    String secondName = req.getParameter("secondName");
    String email = req.getParameter("email");
    String startDate = req.getParameter("startDate");
    String startHireDate = req.getParameter("startHireDate");

    employee.setFirstName(name);
    employee.setSecondName(secondName);
    employee.setEmail(email);

    if (teamId != null) {
      employee.setTeam(teamService.getTeamById(Long.valueOf(teamId)));
    } else {
      employee.setTeam(null);
    }

    if (!startDate.equals("")) {
      employee.setStartDate(LocalDate.parse(startDate));
    } else {
      employee.setStartDate(null);
    }

    if (!startHireDate.equals("")) {
      employee.setStartHireDate(LocalDate.parse(startHireDate));
    } else {
      employee.setStartHireDate(null);
    }

    if (!employeeValidator.isMailUnique(email)) {

      String message = String.format("Email address \"%s\" is already in use!", email);

      userMessagesService.addErrorMessage(req.getSession(), message);

      logger.info("Tried to use an existing email \"{}\"", email);
    }

    if (employeeValidator.areDatesParseable(startDate, startHireDate)) {
      if (!employeeValidator
          .isStartHireDateEarlierThanOrEqualToStartDate(startHireDate, startDate)) {
        String message = "First employment date has to be earlier or equal to actual employment date!";

        userMessagesService.addErrorMessage(req.getSession(), message);

        logger.info("Tried to set actual employment date earlier than first employment date");
      }
    }

    if (!employeeValidator.isEmployeeDataValid(req, employee)) {

      logger.info("An employee \"{} {}\" has not been edited", name, secondName);
    }

    if (userMessagesService.getErrorMessageList(req.getSession()) == null) {
      employeeService.editEmployee(employee, Long.parseLong(teamId));

      String message = String.format("An employee \"%s %s\" has been edited!", name, secondName);

      userMessagesService
          .addSuccessMessage(req.getSession(), message);

      logger.info("An employee \"{} {}\" has been edited", name, secondName);
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
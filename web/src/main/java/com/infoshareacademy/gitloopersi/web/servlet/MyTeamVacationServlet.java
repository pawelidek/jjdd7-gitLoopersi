package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import com.infoshareacademy.gitloopersi.web.view.VacationView;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/vacation/team")
public class MyTeamVacationServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private TeamService teamService;

  @Inject
  private CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");
    String email = String.valueOf(req.getSession().getAttribute("email"));
    Map<String, Object> dataModel = new HashMap<>();
    Employee employee = employeeService.getEmployeeByEmail(email);
    req.getSession().setAttribute("employeeId", employee.getId());
    Long employeeId = (Long) req.getSession().getAttribute("employeeId");
    logger.info("{}", employee.getFirstName());
    Long myTeamId = teamService.getTeamByEmployeeId(employeeId).getId();
    Team team = teamService.getTeamByEmployeeId(employeeId);
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    List<Calendar> vacationDates = calendarService.findTeamEmployeesVacation(team.getId());
    List<VacationView> vacationViews = vacationDefiningService.getVacationsListForTeam(myTeamId);
    List<EmployeeView> employeeViewsFromTeam = employeeService.getEmployeesFromTeam(myTeamId);
    dataModel.put("userType", "user");
    dataModel.put("vacations", vacationViews);
    dataModel.put("employees", employeeViewsFromTeam);
    dataModel.put("team", team);
    dataModel.put("function", "MyTeamVacation");
    dataModel.put("dates", dates);
    dataModel.put("vacationDates", vacationDates);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    vacationDefiningService.deleteVacation(id);
  }
}
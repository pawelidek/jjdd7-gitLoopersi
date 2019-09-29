package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
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
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/vacation")
public class MyVacationServlet extends HttpServlet {

  private static final String EMPLOYEE_ID = "employeeId";

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private UserMessagesService userMessagesService;

  @Inject
  private CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    List<EmployeeView> employeeViews = employeeService.getEmployeesWithTeamsList();
    List<String> errorMessages = userMessagesService.getErrorMessageList(req.getSession());
    List<String> successMessages = userMessagesService.getSuccessMessageList(req.getSession());
    HttpSession httpSession = req.getSession(true);
    String email = String.valueOf(httpSession.getAttribute("email"));
    httpSession.setAttribute(EMPLOYEE_ID, employeeService.getEmployeeByEmail(email).getId());
    Long employeeId = (Long) httpSession.getAttribute(EMPLOYEE_ID);
    List<VacationView> vacationViews = vacationDefiningService
        .getVacationsListForEmployee(employeeId);

    List<EmployeeView> employeeViewList = employeeViews.stream()
        .filter(employeeView -> employeeView.getId().equals(employeeId))
        .collect(Collectors.toList());
    employeeViews.remove(employeeViewList.get(0));

    dataModel.put("userType", "user");
    dataModel.put("vacations", vacationViews);
    dataModel.put("employees", employeeViews);
    dataModel.put("error", errorMessages);
    dataModel.put("success", successMessages);
    dataModel.put("function", "MyVacation");
    dataModel.put("dates", dates);

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
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    vacationDefiningService.deleteVacation(id);
  }
}
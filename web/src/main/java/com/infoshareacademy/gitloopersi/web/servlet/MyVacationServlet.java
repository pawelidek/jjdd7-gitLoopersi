package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.emailmanager.EmailVacationService;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.VacationType;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import com.infoshareacademy.gitloopersi.web.view.VacationView;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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

@WebServlet(urlPatterns = {
    "/user/vacation",
    "/user/vacation/report"
})
public class MyVacationServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private UserMessagesService userMessagesService;

  @EJB
  private EmailVacationService emailVacationService;

  @Inject
  private CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    List<VacationView> vacationViews = vacationDefiningService.getVacationsWithEmployeesList();
    List<EmployeeView> employeeViews = employeeService.getEmployeesWithTeamsList();
    List<String> errorMessages = userMessagesService.getErrorMessageList(req.getSession());
    List<String> successMessages = userMessagesService.getSuccessMessageList(req.getSession());
    HttpSession httpSession = req.getSession(true);
    httpSession.setAttribute("employeeId", 1L);
    Long employeeId = (Long) req.getSession().getAttribute("employeeId");
    List<EmployeeView> employeeViewList = employeeViews.stream()
        .filter(employeeView -> employeeView.getId().equals(employeeId))
        .collect(Collectors.toList());
    employeeViews.remove(employeeViewList.get(0));

    dataModel.put("userType", "user");
    dataModel.put("vacations", vacationViews);
    dataModel.put("employees", employeeViews);
    dataModel.put("error", errorMessages);
    dataModel.put("success", successMessages);
    dataModel.put("function", "VacationDefining");
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
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    Long employeeId = (Long) req.getSession().getAttribute("employeeId");
    Vacation vacation = new Vacation();

//    Employee employee = employeeService.getEmployeeById(employeeId);

    setVacationFields(req, vacation);
    vacationDefiningService.addVacation(vacation, employeeId);

    //emailVacationService.buildEmailMessage(vacation, employee);

    logger.info("Vacation {} was added", vacation.toString());
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    vacationDefiningService.deleteVacation(id);
  }

  private void setVacationFields(HttpServletRequest req, Vacation vacation) {

    int numberOfSelectedVacationDays = getNumberOfSelectedVacationDays(req);

    LocalDate dateFrom = LocalDate.parse(req.getParameter("dateFrom"));
    LocalDate dateTo = LocalDate.parse(req.getParameter("dateTo"));
    String vacationType = req.getParameter("vacationType");
    String deputy = req.getParameter("deputy");

    vacation.setDateFrom(dateFrom);
    vacation.setDateTo(dateTo);
    vacation.setDaysCount(numberOfSelectedVacationDays);
    vacation.setVacationType(VacationType.valueOf(vacationType));
    vacation.setDeputy(deputy);
  }

  private int getNumberOfSelectedVacationDays(HttpServletRequest req) {
    return vacationDefiningService
        .getNumberOfSelectedVacationDays(req.getParameter("dateFrom"),
            req.getParameter("dateTo"));
  }
}
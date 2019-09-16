package com.infoshareacademy.gitloopersi.servlet.user;

import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.CalendarService;
import com.infoshareacademy.gitloopersi.service.UserMessagesService;
import com.infoshareacademy.gitloopersi.vacation.service.VacationDefiningService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
  private UserMessagesService userMessagesService;

  @Inject
  private CalendarService calendarService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    dataModel.put("userType", "user");
    dataModel.put("function", "VacationDefining");
    dataModel.put("dates", dates);

    dataModel.put("errorMessage", userMessagesService
        .getErrorMessage(req.getSession(), "errorMessage"));

    dataModel.put("successMessage",
        userMessagesService.getSuccessMessage(req.getSession(), "successMessage"));

    removeErrorMessage(req);
    removeSuccessMessage(req);

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

    Long employeeId = 1L;
    Vacation vacation = new Vacation();

    setVacationFields(req, vacation);
    vacationDefiningService.addVacation(vacation, employeeId);
    logger.info("Vacation {} was added", vacation.toString());
    resp.sendRedirect("/user/vacation");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String dateFrom = req.getParameter("dateFrom");
    String dateTo = req.getParameter("dateTo");
    Long employeeId = 1L;
  }

  private void setVacationFields(HttpServletRequest req, Vacation vacation) {

    int numberOfSelectedVacationDays = getNumberOfSelectedVacationDays(req);

    LocalDate dateFrom = LocalDate.parse(req.getParameter("dateFrom"));
    LocalDate dateTo = LocalDate.parse(req.getParameter("dateTo"));

    vacation.setDateFrom(dateFrom);
    vacation.setDateTo(dateTo);
    vacation.setDaysCount(numberOfSelectedVacationDays);
  }

  private int getNumberOfSelectedVacationDays(HttpServletRequest req) {
    return vacationDefiningService
        .getNumberOfSelectedVacationDays(req.getParameter("dateFrom"), req.getParameter("dateTo"));
  }


  private void removeErrorMessage(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute("errorMessage");

  }

  private void removeSuccessMessage(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute("successMessage");
  }

}
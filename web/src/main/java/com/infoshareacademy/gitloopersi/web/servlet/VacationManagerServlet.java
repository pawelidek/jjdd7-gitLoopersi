package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.validator.SearchHolidayValidator;
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
  private TemplateProvider templateProvider;

  @Inject
  private CalendarService calendarService;

  @EJB
  private VacationDefiningService vacationDefiningService;

  @EJB
  private UserMessagesService userMessagesService;


  @Inject
  SearchHolidayValidator searchHolidayValidator;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<Calendar> dates = calendarService.findAllHolidaysDates();

    String dateFrom = req.getParameter("dateFrom");
    String dateTo = req.getParameter("dateTo");
    logger.info("Dates dateFrom={} and dateTo={} are to be validated", dateFrom, dateTo);
    if (searchHolidayValidator.checkIsDateFormatValid(dateFrom, dateTo)) {
      if (searchHolidayValidator.checkIsEndDateLaterThanStartDate(dateFrom, dateTo)) {
      } else {
        dateFrom="1970-01-01";
        dateTo="2100-01-01";
      }
    } else {
      dateFrom="1970-01-01";
      dateTo="2100-01-01";
    }

    List<VacationView> vacationViews = vacationDefiningService.getVacationsWithEmployeesList(dateFrom,dateTo);
    List<String> successMessages = userMessagesService.getSuccessMessageList(req.getSession());

    dataModel.put("userType", "admin");
    dataModel.put("vacations", vacationViews);
    dataModel.put("success", successMessages);
    dataModel.put("function", "VacationManager");
    dataModel.put("dates", dates);

    userMessagesService.removeSuccessMessages(req);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}
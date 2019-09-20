package com.infoshareacademy.gitloopersi.web.servlet;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.domain.model.Calendar;
import com.infoshareacademy.gitloopersi.exception.TeamNotEmptyException;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.calendarmanager.CalendarService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/team")
public class TeamManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private TeamService teamService;

  @Inject
  private CalendarService calendarService;

  @EJB
  private UserMessagesService userMessagesService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<Team> teamList = teamService.getTeamList();
    List<Calendar> dates = calendarService.findAllHolidaysDates();
    List<String> errorMessages = userMessagesService.getErrorMessageList(req.getSession());
    List<String> successMessages = userMessagesService.getSuccessMessageList(req.getSession());

    dataModel.put("userType", "admin");
    dataModel.put("teams", teamList);
    dataModel.put("function", "TeamManager");
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

    Team team = new Team();

    String name = req.getParameter("name");

    team.setName(name);

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    List<String> errorMessages = new ArrayList<>();
    List<String> successMessages = new ArrayList<>();

    if (teamService.getTeamByName(name) != null) {
      errorMessages.add("Team \"" + name + "\" already exists, enter another team name!");
    }

    Set<ConstraintViolation<Team>> constraintViolations = validator.validate(team);

    if (doesErrorsOccur(errorMessages, constraintViolations)) {

      if (constraintViolations.size() > 0) {
        errorMessages.addAll(
            constraintViolations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));
      }

      logger.info("{}", successMessages);
      req.getSession().setAttribute("errorMessage", errorMessages);
    } else {
      teamService.addTeam(team);
      successMessages.add("A new team \"" + name + "\" has been added!");
      logger.info("{}", successMessages);
      req.getSession().setAttribute("successMessage", successMessages);
    }
  }

  private boolean doesErrorsOccur(List<String> errorMessages,
      Set<ConstraintViolation<Team>> constraintViolations) {
    return constraintViolations.size() > 0 || errorMessages.size() > 0;
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Long id = Long.parseLong(req.getParameter("id"));
    Team team = teamService.getTeamById(id);

    String name = req.getParameter("name");

    team.setName(name);
    teamService.editTeam(team);
    logger.info("A team with id={} has been edited!", id);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    try {
      teamService.deleteTeam(id);
      logger.info("A team with id={} has been deleted!", id);
    } catch (TeamNotEmptyException e) {
      logger.info("A team with id={} contains employees and cannot be deleted!", id);
    }
  }

}
package com.infoshareacademy.gitloopersi.servlet.admin;

import com.infoshareacademy.gitloopersi.domain.Calendar;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.exception.TeamNotEmptyException;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.CalendarService;
import com.infoshareacademy.gitloopersi.service.TeamService;
import com.infoshareacademy.gitloopersi.service.UserMessagesService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/admin/team")
public class TeamManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private TeamService teamService;

  @Inject
  private CalendarService calendarService;

//  @EJB
//  private UserMessagesService userMessagesService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    List<Team> teamList = teamService.getTeamList();
    String error = String.valueOf(req.getSession().getAttribute("errorMessage"));

    List<Calendar> dates = calendarService.findAllHolidaysDates();


    dataModel.put("userType", "admin");
    dataModel.put("teams", teamList);
    dataModel.put("function", "TeamManager");
    dataModel.put("method", "put");
    dataModel.put("error", error);
    dataModel.put("dates", dates);

//    dataModel.put("errorMessage", userMessagesService
//        .getErrorMessage(req.getSession(), "errorMessage"));
//
//    dataModel.put("successMessage",
//        userMessagesService.getSuccessMessage(req.getSession(), "successMessage"));

//    removeErrorMessage(req);
//    removeSuccessMessage(req);

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
    teamService.addTeam(team);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Long id = Long.parseLong(req.getParameter("id"));
    Team team = teamService.getTeamById(id);

    String name = req.getParameter("name");

    team.setName(name);
    teamService.editTeam(team);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    try {
      teamService.deleteTeam(id);
    } catch (TeamNotEmptyException e) {
//      req.getSession()
//          .setAttribute("errorMessage", "A team containing employees cannot be deleted!");
      logger.info("A team with id={} contains employees and cannot be deleted!", id);
    }
  }

//  private void removeErrorMessage(HttpServletRequest req) {
//    Objects.requireNonNull(req.getSession()).removeAttribute("errorMessage");
//
//  }
//
//  private void removeSuccessMessage(HttpServletRequest req) {
//    Objects.requireNonNull(req.getSession()).removeAttribute("successMessage");
//  }
}
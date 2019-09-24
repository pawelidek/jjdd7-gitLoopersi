package com.infoshareacademy.gitloopersi.validator;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.service.alertmessage.UserMessagesService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@RequestScoped
public class TeamValidator {

  @EJB
  private TeamService teamService;

  @EJB
  private UserMessagesService userMessagesService;

  public boolean isTeamUnique(String name) {
    return teamService.getTeamByName(name) == null;
  }

  public boolean isTeamDataValid(HttpServletRequest req, Team team) {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    Set<ConstraintViolation<Team>> constraintViolations = validator.validate(team);

    if (constraintViolations.size() > 0) {

      List<String> errorsList = constraintViolations.stream().map(ConstraintViolation::getMessage)
          .collect(Collectors.toList());

      req.getSession().setAttribute("errorsListFromValidator", errorsList);

      return false;
    } else {
      return true;
    }
  }
}
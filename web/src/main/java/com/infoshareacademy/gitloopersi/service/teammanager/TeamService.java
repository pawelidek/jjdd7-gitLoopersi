package com.infoshareacademy.gitloopersi.service.teammanager;

import com.infoshareacademy.gitloopersi.dao.TeamDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.web.mapper.EmployeeViewMapper;
import com.infoshareacademy.gitloopersi.web.mapper.TeamViewMapper;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private TeamDaoBean teamDaoBean;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private TeamViewMapper teamViewMapper;

  @EJB
  private EmployeeViewMapper employeeViewMapper;

  public void addTeam(Team team) {
    logger.info("New team object [{}] go to DAO to be saved in DB", team.getName());
    teamDaoBean.addTeam(team);
  }

  public Team editTeam(Team team) {
    logger.info("Team [{}] go to DAO to be modified in DB", team.getName());
    return teamDaoBean.editTeam(team);
  }

  public Team getTeamById(Long id) {
    logger.info("Team object id={} go to DAO to be found in DB", id);
    return teamDaoBean.getTeamById(id);
  }

  public void deleteTeam(Long id) {
    logger.info("Team object id={} go to DAO to be removed in DB", id);
    teamDaoBean.deleteTeamById(id);
  }


  public List<Team> getTeamList() {
    return teamDaoBean.getTeamsList();
  }

  public boolean isTeamEmpty(Long id) {
    if (teamDaoBean.getEmployeeCountInTeam(id) == 0) {
      logger.info("Team id={} is empty", id);
      return true;
    } else {
      logger.info("Team id={} is not empty", id);
      return false;
    }
  }

  public Team getTeamByEmployeeId(Long id) {
    logger.info("Team object for employee with id={} go to DAO to be found in DB", id);
    return teamDaoBean.getTeamByEmployeeId(id);
  }

  public Team getTeamByName(String name) {
    return teamDaoBean.getTeamByName(name);
  }

}
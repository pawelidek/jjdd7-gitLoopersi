package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.TeamDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.exception.TeamNotEmptyException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class TeamService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private TeamDaoBean teamDaoBean;

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

  public void deleteTeam(Long id) throws TeamNotEmptyException {
    logger.info("Team object id={} go to DAO to be removed in DB", id);
    if (isTeamEmpty(id)) {
      teamDaoBean.deleteTeamById(id);
    } else {
      throw new TeamNotEmptyException();
    }
  }

  public List<Team> getTeamList() {
    logger.info("Objects team go to DAO to be found in DB");
    return teamDaoBean.getTeamsList();
  }

  private boolean isTeamEmpty(Long id) {
    return teamDaoBean.getTeamById(id).getTeamEmployees().size() == 0;
  }
}
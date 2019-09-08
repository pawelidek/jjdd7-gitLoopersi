package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.TeamDao;
import com.infoshareacademy.gitloopersi.entity.Team;
import java.util.List;
import javax.ejb.EJB;

public class TeamService {

  @EJB
  private TeamDao teamDao;

  public void addTeam(Team team) {
    teamDao.addTeam(team);
  }

  public Team editTeam(Team team) {
    return teamDao.editTeam(team);
  }

  public Team getTeamById(Long id) {
    return teamDao.getTeamById(id);
  }

  public void deleteTeam(Long id) {
    teamDao.deleteTeamById(id);
  }

  public List<Team> getTeamList() {
    return teamDao.getTeamsList();
  }

}

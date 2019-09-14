package com.infoshareacademy.gitloopersi.service;

import com.infoshareacademy.gitloopersi.dao.TeamDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TeamService {

  @EJB
  private TeamDaoBean teamDaoBean;

  public void addTeam(Team team) {
    teamDaoBean.addTeam(team);
  }

  public Team editTeam(Team team) {
    return teamDaoBean.editTeam(team);
  }

  public Team getTeamById(Long id) {
    return teamDaoBean.getTeamById(id);
  }

  public void deleteTeam(Long id) {
    teamDaoBean.deleteTeamById(id);
  }

  public List<Team> getTeamList() {
    return teamDaoBean.getTeamsList();
  }

}

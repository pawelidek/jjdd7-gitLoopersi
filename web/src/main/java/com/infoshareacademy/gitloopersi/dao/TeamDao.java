package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Team;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TeamDao {

  void addTeam(Team team);

  Team editTeam(Team team);

  Team getTeamById(Long id);

  void deleteTeamById(Long id);

  List<Team> getTeamsList();

}
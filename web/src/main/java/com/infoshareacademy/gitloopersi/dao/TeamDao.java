package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TeamDao {

  @PersistenceContext
  EntityManager entityManager;

  public void addTeam(Team team) {
    entityManager.persist(team);
  }

  public Team editTeam(Team team) {
    return entityManager.merge(team);
  }

  public Team getTeamById(Long id) {
    return entityManager.find(Team.class, id);
  }

  public void deleteTeamById(Long id) {
    Team team = getTeamById(id);
    if (team != null) {
      entityManager.remove(team);
    }
  }

  public List<Team> getTeamsList() {
    Query query = entityManager.createQuery("select t from Team t");
    return query.getResultList();
  }
}
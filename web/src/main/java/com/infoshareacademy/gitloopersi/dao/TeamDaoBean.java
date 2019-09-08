package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TeamDaoBean implements TeamDao {

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public void addTeam(Team team) {
    entityManager.persist(team);
  }

  @Override
  public Team editTeam(Team team) {
    return entityManager.merge(team);
  }

  @Override
  public Team getTeamById(Long id) {
    return entityManager.find(Team.class, id);
  }

  @Override
  public void deleteTeamById(Long id) {
    Team team = getTeamById(id);
    if (team != null) {
      entityManager.remove(team);
    }
  }

  @Override
  public List<Team> getTeamsList() {
    Query query = entityManager.createQuery("select t from Team t");
    return query.getResultList();
  }
}
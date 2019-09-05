package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.entity.Team;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TeamDaoBean {

  @PersistenceContext
  EntityManager entityManager;

  public void saveTeam(String teamName) {
    Team team = new Team(teamName);
    entityManager.persist(team);
  }
}

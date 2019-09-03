package com.infoshareacademy.gitLoopersi.dao;

import com.infoshareacademy.gitLoopersi.entity.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TeamDaoBean {

    @PersistenceContext
    EntityManager entityManager;

    public void saveTeam(String teamName){
        Team team = new Team(teamName);
        entityManager.persist(team);
    }
}

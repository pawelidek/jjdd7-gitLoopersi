package com.infoshareacademy.gitloopersi.dao;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class VacationDaoBean {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PersistenceContext
  EntityManager entityManager;

  public void addVacation(Vacation vacation) {
    logger.info("Object {} vacation persist to DB", vacation.toString());
    entityManager.persist(vacation);
  }

  public void deleteVacation(Vacation vacation) {
    logger.info("Object {} vacation delete from DB", vacation.toString());
    entityManager.remove(vacation);
  }

  public List<Vacation> getVacationsList() {
    Query query = entityManager.createNamedQuery("Vacation.findAll");
    return query.getResultList();
  }
}

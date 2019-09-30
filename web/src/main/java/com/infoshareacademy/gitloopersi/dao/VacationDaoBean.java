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

  @PersistenceContext
  EntityManager entityManager;
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public void addVacation(Vacation vacation) {
    logger.info("Object {} vacation persist to DB", vacation.toString());
    entityManager.persist(vacation);
  }

  public Vacation editVacation(Vacation vacation) {
    logger.info("Vacation object id={} is to be updated in DB", vacation.getId());
    return entityManager.merge(vacation);
  }

  public Vacation getVacationById(Long id) {
    logger.info("Vacation object id={} is to be get from DB", id);
    return entityManager.find(Vacation.class, id);
  }

  public void deleteVacation(Long id) {
    logger.info("Object id = {} vacation delete from DB", id);
    Vacation vacation = getVacationById(id);
    if (vacation != null) {
      entityManager.remove(vacation);
    } else {
      logger.warn("An attempt to delete non-existing vacation object id={} has occurred", id);
    }
  }

  public List<Vacation> getVacationsList() {
    logger.info("Vacation objects are to be get from DB");
    Query query = entityManager.createNamedQuery("Vacation.findAll");
    return query.getResultList();
  }

  public List<Vacation> getVacationsListForTeam(Long id) {
    logger.info("Vacation objects for team id={} are to be get from DB", id);
    Query query = entityManager.createNamedQuery("Vacation.findAllInTeam");
    query.setParameter("id", id);
    return query.getResultList();
  }

  public List<Vacation> getVacationsListForEmployee(Long id) {
    logger.info("Vacation objects for employee id={} are to be get from DB", id);
    Query query = entityManager.createNamedQuery("Vacation.findAllForEmployee");
    query.setParameter("id", id);
    return query.getResultList();
  }
}
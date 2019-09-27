package com.infoshareacademy.gitloopersi.dao;


import com.infoshareacademy.gitloopersi.domain.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserDaoBean {

  @PersistenceContext
  EntityManager entityManager;
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Transactional
  public void save(User user) {
    entityManager.persist(user);
  }

  @Transactional
  public User updateUser(User user) {
    return entityManager.merge(user);
  }

  @Transactional
  public User getUserById(Integer id) {
    return entityManager.find(User.class, id);
  }

  public void deleteUserById(Integer id) {
    User user = getUserById(id);
    if (user != null) {
      entityManager.remove(user);
    }
  }
  public List<User> getUsersList() {
    Query query = entityManager.createNamedQuery("User.getUserList");
    return query.getResultList();
  }

  @Transactional
  public User findUserByEmail(String email) {
    Query query = entityManager.createNamedQuery("User.findUserByEmail");
    query.setParameter("email", email);
    return (User) query.getResultList().stream().findFirst().orElse(null);
  }
}

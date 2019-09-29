package com.infoshareacademy.gitloopersi.service.usermanager;

import com.infoshareacademy.gitloopersi.dao.UserDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private UserDaoBean userDaoBean;

  public void addUser(User user) {
    logger.info("New user object {} go to DAO to be saved in DB", user.getName());
    userDaoBean.save(user);
  }

  public User updateUser(User user) {
    logger.info("User {} go to DAO to be modified in DB", user.getName());
    return userDaoBean.updateUser(user);
  }

  public User findUserByEmail(String email) {
    logger.info("Objects user go to DAO to be found in DB");
    return userDaoBean.findUserByEmail(email);
  }

  public User getUserById(Long id) {
    return userDaoBean.getUserById(id);
  }

  public void deleteUserById(Long id) {
    userDaoBean.deleteUserById(id);
  }
}
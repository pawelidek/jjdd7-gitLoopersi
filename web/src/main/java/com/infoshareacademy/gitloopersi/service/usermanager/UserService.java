package com.infoshareacademy.gitloopersi.service.usermanager;

import com.infoshareacademy.gitloopersi.dao.UserDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService {

  @EJB
  private UserDaoBean userDaoBean;

  public void save(User user) {
    userDaoBean.save(user);

  }

  public User updateUser(User user) {
    return userDaoBean.updateUser(user);
  }

  public User findUserByEmail(String email) {

    return userDaoBean.findUserByEmail(email);
  }

  public User getUserById(Integer id) {
    return userDaoBean.getUserById(id);
  }

  public void deleteUserById(Integer id) {
    userDaoBean.deleteUserById(id);

  }

}

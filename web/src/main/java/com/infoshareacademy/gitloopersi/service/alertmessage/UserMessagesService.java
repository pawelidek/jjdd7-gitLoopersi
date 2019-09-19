package com.infoshareacademy.gitloopersi.service.alertmessage;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
public class UserMessagesService {

  public String getErrorMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }

  public String getSuccessMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }
}

package com.infoshareacademy.gitloopersi.service;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
public class UserMessagesService {

  public String getErrorMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }
}

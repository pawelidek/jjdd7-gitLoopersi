package com.infoshareacademy.gitloopersi.handler;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
public class UserMessageHandler {

  public void setErrorMessage(HttpSession session, String key, Object message) {
    if (message != null) {
      session.setAttribute(key, message);
    }
  }
}

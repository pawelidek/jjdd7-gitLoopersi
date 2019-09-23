package com.infoshareacademy.gitloopersi.service.alertmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Stateless
public class UserMessagesService {

  private static final String ERROR_MESSAGE = "errorMessage";
  public static final String SUCCESS_MESSAGE = "successMessage";

  public List<String> getErrorMessageList(HttpSession httpSession) {
    return (List<String>) httpSession.getAttribute(ERROR_MESSAGE);
  }

  public void addErrorMessage(HttpSession httpSession, String message) {

    if (getErrorMessageList(httpSession) == null) {
      List<String> errorMessages = new ArrayList<>();
      errorMessages.add(message);
      httpSession.setAttribute(ERROR_MESSAGE, errorMessages);
    } else {
      List<String> errorMessages = getErrorMessageList(httpSession);
      errorMessages.add(message);
      httpSession.setAttribute(ERROR_MESSAGE, errorMessages);
    }
  }

  public List<String> getSuccessMessageList(HttpSession httpSession) {
    return (List<String>) httpSession.getAttribute(SUCCESS_MESSAGE);
  }

  public void addSuccessMessage(HttpSession httpSession, String message) {

    if (getSuccessMessageList(httpSession) == null) {
      List<String> successMessages = new ArrayList<>();
      successMessages.add(message);
      httpSession.setAttribute(SUCCESS_MESSAGE, successMessages);
    } else {
      List<String> successMessages = getSuccessMessageList(httpSession);
      successMessages.add(message);
      httpSession.setAttribute(SUCCESS_MESSAGE, successMessages);
    }
  }

  public void removeErrorMessages(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute(ERROR_MESSAGE);
  }

  public void removeSuccessMessages(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute(SUCCESS_MESSAGE);
  }

  @Deprecated
  public String getErrorMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }

  @Deprecated
  public String getSuccessMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }
}
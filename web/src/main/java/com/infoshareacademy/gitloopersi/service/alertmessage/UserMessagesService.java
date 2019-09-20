package com.infoshareacademy.gitloopersi.service.alertmessage;

import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Stateless
public class UserMessagesService {

  public List<String> getErrorMessageList(HttpSession httpSession) {
    return (List<String>) httpSession.getAttribute("errorMessage");
  }

  public void addErrorMessage(HttpSession httpSession, String message) {
    List<String> errorMessages = getErrorMessageList(httpSession);
    errorMessages.add(message);
    httpSession.setAttribute("errorMessage", errorMessages);
  }

  public List<String> getSuccessMessageList(HttpSession httpSession) {
    return (List<String>) httpSession.getAttribute("successMessage");
  }

  public void addSuccessMessage(HttpSession httpSession, String message) {
    List<String> successMessages = getSuccessMessageList(httpSession);
    successMessages.add(message);
    httpSession.setAttribute("errorMessage", successMessages);
  }

  public String getErrorMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }

  public String getSuccessMessage(HttpSession session, String key) {
    return (String) session.getAttribute(key);
  }

  public void removeErrorMessages(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute("errorMessage");
  }

  public void removeSuccessMessages(HttpServletRequest req) {
    Objects.requireNonNull(req.getSession()).removeAttribute("successMessage");
  }
}

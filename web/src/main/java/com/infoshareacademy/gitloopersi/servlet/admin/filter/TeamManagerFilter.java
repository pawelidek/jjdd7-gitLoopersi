package com.infoshareacademy.gitloopersi.servlet.admin.filter;

import com.infoshareacademy.gitloopersi.handler.UserMessageHandler;
import com.infoshareacademy.gitloopersi.team.validator.TeamValidator;
import java.io.IOException;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "TeamManagerFilter",
    urlPatterns = {"/admin/team"}
)
public class TeamManagerFilter implements Filter {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TeamValidator teamValidator;

//  @EJB
//  private UserMessageHandler userMessageHandler;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    HttpSession httpSession = httpServletRequest.getSession(true);

    String name = httpServletRequest.getParameter("name");
    String redirect = "/admin/team";

    if (isAddingOrEditing(httpServletRequest)) {

      if (isNullEmptyOrWhitespaceOnly(name)) {

//        userMessageHandler.setErrorMessage(httpSession, "errorMessage",
//            "An empty team name cannot be set!!");
//        httpServletResponse.sendRedirect(redirect);

        logger.warn("Team name {} is null, empty or whitespace only", name);
//        httpSession.setAttribute("errorMessage", "Fill team name field!");
        httpServletResponse.sendRedirect(redirect);
      } else {

        logger.info("Team name {} is not null, empty or whitespace only", name);

        if (!consistsOfLettersDigitsSpaces(name)) {
          logger.warn("Team name {} is not in alphanumeric space", name);
//          httpSession.setAttribute("errorMessage",
//              "Team name is not alphanumeric only!");
          httpServletResponse.sendRedirect(redirect);
        } else {

          logger.info("Team name {} is in alphanumeric space", name);

          if (alreadyExists(name)) {
            logger.warn("Team name {} already exists in DB", name);
//            httpSession.setAttribute("errorMessage",
//                "Team already exists!");
            httpServletResponse.sendRedirect(redirect);
          } else {

            logger.info("Team name {} is unique in DB", name);
            filterChain.doFilter(servletRequest, servletResponse);
          }
        }
      }
    } else {

      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  private boolean isAddingOrEditing(HttpServletRequest httpServletRequest) {
    return httpServletRequest.getMethod().equals(HttpMethod.POST)
        || httpServletRequest.getMethod().equals(HttpMethod.PUT);
  }

  private boolean alreadyExists(String name) {
    return teamValidator.alreadyExists(name);
  }

  private boolean consistsOfLettersDigitsSpaces(String name) {
    return teamValidator.isAlphanumericSpace(name);
  }

  private boolean isNullEmptyOrWhitespaceOnly(String name) {
    return teamValidator.isBlank(name);
  }


}
package com.infoshareacademy.gitloopersi.googleoauth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.domain.entity.User;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.service.usermanager.UserService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oauth2callback")
public class LoginCallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

  @EJB
  private UserService userService;

  @EJB
  private TeamService teamService;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private OauthBuilder builderOauth;

  @Override
  protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
      throws ServletException, IOException {
    GoogleCredential gCredential = new GoogleCredential()
        .setAccessToken(credential.getAccessToken());

    Oauth2 oauth2 = builderOauth.buildOauth(gCredential);

    Userinfoplus info = oauth2.userinfo().get().execute();
    String name = info.getGivenName();
    String email = info.getEmail();
    String surname = info.getFamilyName();

    if (teamService.getTeamByName("Unkown") == null) {
      Team team = new Team();
      team.setName("Unkown");
      teamService.addTeam(team);
    }

    if (employeeService.getEmployeeByEmail(email) == null) {
      Team team = teamService.getTeamByName("Unkown");
      User user = new User();
      Employee employee = new Employee();
      team.setName("Unkown");
      user.setName(name);
      user.setSurname(surname);
      user.setEmail(email);
      user.setEmployee(employee);
      employee.setEmail(email);
      employee.setFirstName(name);
      employee.setSecondName(surname);
      employee.setStartHireDate(LocalDate.now());
      employee.setStartDate(LocalDate.now());
      employee.unsetAdminPermissions();
      employee.setTeam(team);
      teamService.editTeam(team);
      employeeService.addEmployee(employee, team.getId());
      userService.updateUser(user);

    }

    Employee employee = employeeService.getEmployeeByEmail(email);
    req.getSession().setAttribute("email", employee.getEmail());
    if (employee.isAdmin()) {
      req.getSession().setAttribute("userType", "admin");
    } else {
      req.getSession().setAttribute("userType", "user");
    }
    resp.sendRedirect("/home");
  }

  @Override
  protected void onError(
      HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
      throws ServletException, IOException {
  }

  @Override
  protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
    return GoogleLogin.buildRedirectUri(req);
  }

  @Override
  protected AuthorizationCodeFlow initializeFlow() throws IOException {
    return GoogleLogin.buildFlow();
  }

  @Override
  protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
    return UUID.randomUUID().toString();
  }
}
package com.infoshareacademy.gitloopersi.servlet.admin;

import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.EmployeeService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = {
    "/admin/employee",
    "/admin/employee/add",
    "/admin/employee/edit"
})
public class EmployeeManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private EmployeeService employeeService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    Employee e1 = new Employee();
    e1.setFirstName("Franciszek");
    e1.setSecondName("Kowalski");
    e1.setStartDate(LocalDate.of(2000,8,12));
    e1.setStartHireDate(LocalDate.of(1995,6,8));
    employeeService.addEmployee(e1);

    Employee e2 = new Employee();
    e2.setFirstName("Grzegorz");
    e2.setSecondName("Brzeczyszczykiewicz-Wisniowiecki");
    e2.setStartDate(LocalDate.of(2002,10,14));
    e2.setStartHireDate(LocalDate.of(1997,8,10));
    employeeService.addEmployee(e2);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    String servletPath = req.getServletPath();

    Map<String, Object> dataModel = new HashMap<>();

    dataModel.put("userType", "admin");

    if (servletPath.equals("/admin/employee")) {

      List<Employee> employeeList = employeeService.getEmployeesList();
      dataModel.put("employees", employeeList);
      dataModel.put("function", "EmployeeManager");

    } else if (servletPath.equals("/admin/employee/add")) {

      dataModel.put("function", "EmployeeForm");
      dataModel.put("method", "");

    } else if (servletPath.equals("/admin/employee/edit")) {

      Long id = Long.parseLong(req.getParameter("id"));
      Employee employee = employeeService.getEmployeeById(id);
      dataModel.put("employee", employee);
      dataModel.put("function", "EmployeeForm");
      dataModel.put("method", "put");

    }

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Employee employee = new Employee();

    String name = req.getParameter("firstName");
    String secondName = req.getParameter("secondName");
    LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
    LocalDate startHireDate = LocalDate.parse(req.getParameter("startHireDate"));

    employee.setFirstName(name);
    employee.setSecondName(secondName);
    employee.setStartDate(startDate);
    employee.setStartHireDate(startHireDate);

    employeeService.addEmployee(employee);

    resp.sendRedirect("/admin/employee");
  }

    @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

      Long id = Long.parseLong(req.getParameter("id"));
      Employee employee = employeeService.getEmployeeById(id);

      String newName = req.getParameter("newName");
      String newSecondName = req.getParameter("newSecondName");
      LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
      LocalDate startHireDate = LocalDate.parse(req.getParameter("startHireDate"));

      employee.setFirstName(newName);
      employee.setSecondName(newSecondName);
      employee.setStartDate(startDate);
      employee.setStartHireDate(startHireDate);

      employeeService.editEmployee(employee);

      resp.sendRedirect("/admin/employee");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idParam = req.getParameter("id");
    Long id = Long.parseLong(idParam);
    employeeService.deleteEmployeeById(id);
  }

}
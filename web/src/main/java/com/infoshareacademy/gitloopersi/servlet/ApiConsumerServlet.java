package com.infoshareacademy.gitloopersi.servlet;

import com.infoshareacademy.gitloopersi.service.ApiConsumerService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api")
public class ApiConsumerServlet extends HttpServlet {

  @EJB
  private ApiConsumerService apiConsumerService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();
    writer.println("Hello");

    writer.println(apiConsumerService.loadData());
  }
}

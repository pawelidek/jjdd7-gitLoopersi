package com.infoshareacademy.gitloopersi.servlet;

import com.infoshareacademy.gitloopersi.parser.Parser;
import com.infoshareacademy.gitloopersi.service.HolidayApiConsumer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api")
public class ApiConsumerServlet extends HttpServlet {

  @EJB
  private HolidayApiConsumer holidayApiConsumer;

  @Inject
  private Parser parser;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();

    writer.println(holidayApiConsumer.consume());
    writer.println(parser.parseHolidays(new File
        ("/home/maciej/jjdd7-gitLoopersi/HolidaysApi.json")));
  }
}

package com.infoshareacademy.gitloopersi.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "CodingUTF8Filter",
    urlPatterns = {"/*"}
)
public class CodingUTF8Filter implements Filter {

  private final String codingType = "UTF-8";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    logger.info("Coding type is to set");
    servletResponse.setCharacterEncoding(codingType);
    filterChain.doFilter(servletRequest, servletResponse);
  }
}

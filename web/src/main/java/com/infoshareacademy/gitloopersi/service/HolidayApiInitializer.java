package com.infoshareacademy.gitloopersi.service;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class HolidayApiInitializer {

  @EJB
  private HolidayApiConsumer holidayApiConsumer;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PostConstruct
  protected void init() {
    try {
      holidayApiConsumer.consume();
      holidayApiConsumer.loadDataToDataBase();
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}

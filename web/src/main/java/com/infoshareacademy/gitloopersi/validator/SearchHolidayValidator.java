package com.infoshareacademy.gitloopersi.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class SearchHolidayValidator {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private final Logger logger = LoggerFactory.getLogger(getClass().getName());

  public boolean checkIsDateFormatValid(String startDate, String endDate) {
    logger.info("Validate correct format startDate={} and endDate={}", startDate, endDate);
    try {
      simpleDateFormat.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      simpleDateFormat.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      logger.info("Both startDate={} and endDate={} are in correct format", startDate, endDate);
    } catch (ParseException pe) {
      logger.warn("Either startDate={} or endDate={} or both in incorrect format", startDate,
          endDate);
      return false;
    }
    return true;
  }

  public boolean checkIsEndDateLaterThanStartDate(String startDate, String endDate) {
    logger.info("Check if startDate={} is before endDate={}", startDate, endDate);
    LocalDate dateFrom = LocalDate.parse(startDate);
    LocalDate dateTo = LocalDate.parse(endDate);
    Integer compareResult = dateFrom.compareTo(dateTo);
    if (compareResult >= 0) {
      logger.warn("StartDate={} is after endDate={}", startDate, endDate);
      return false;
    } else {
      logger.info("StartDate={} is before endDate={}", startDate, endDate);
      return true;
    }
  }
}

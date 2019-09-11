package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Holiday> mapApiToEntity(List<HolidayApi> holidayApiList) {

    logger.info("Map API to entity");
    List<Holiday> holidays = new ArrayList<>();

    holidayApiList.forEach(holidayApi -> {
      Holiday holiday = new Holiday();
      holiday.setName(holidayApi.getName());
      holiday.setDate(convertToDate(holidayApi.getDate().getIso()));
      holiday.setDescription(holidayApi.getDescription());
      holiday.setHolidayType(holidayApi.getHolidayType().get(0));
      holidays.add(holiday);
    });
    return holidays;
  }

  private LocalDate convertToDate(String dateToConvert) {
    logger.info("Formatting {}", dateToConvert);
    if (dateToConvert.length() > 10) {
      return LocalDate.parse(dateToConvert.substring(0, 10));
    } else {
      return LocalDate.parse(dateToConvert);
    }
  }
}

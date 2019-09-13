package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.Date;
import com.infoshareacademy.gitloopersi.domain.api.Holiday;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HolidayMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<com.infoshareacademy.gitloopersi.domain.entity.Holiday> mapApiToEntity(List<Holiday> holidayList) {

    logger.info("Map API to entity");
    List<com.infoshareacademy.gitloopersi.domain.entity.Holiday> holidays = new ArrayList<>();

    holidayList.forEach(holidayApi -> {
      com.infoshareacademy.gitloopersi.domain.entity.Holiday holiday = new com.infoshareacademy.gitloopersi.domain.entity.Holiday();
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

  public Holiday mapEntityToApi(com.infoshareacademy.gitloopersi.domain.entity.Holiday holidayEntity) {
    Holiday holidayToJSON = new Holiday();
    holidayToJSON.setName(holidayEntity.getName());

    Date date = new Date();
    date.setIso(holidayEntity.getDate().toString());

    holidayToJSON.setDate(date);

    holidayToJSON.setHolidayType(List.of(holidayEntity.getHolidayType()));
    holidayToJSON.setDescription(holidayEntity.getDescription());

    logger.info("Holiday has been mapped to API");

    return holidayToJSON;
  }
}

package com.infoshareacademy.gitloopersi.web.mapper;

import com.infoshareacademy.gitloopersi.domain.jsonapi.Date;
import com.infoshareacademy.gitloopersi.domain.jsonapi.HolidayResponse;
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

  public List<Holiday> mapApiToEntity(List<HolidayResponse> holidayList) {

    List<Holiday> holidays = new ArrayList<>();

    holidayList.forEach(holidayApi -> {
      Holiday holiday = new Holiday();
      holiday.setName(holidayApi.getName());
      holiday.setDate(convertToDate(holidayApi.getDate().getIso()));
      holiday.setDescription(holidayApi.getDescription());
      holiday.setHolidayType(holidayApi.getHolidayType().get(0));
      holidays.add(holiday);
    });

    logger.info("Holiday response has been mapped to entity");

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

  public HolidayResponse mapEntityToApi(Holiday holidayEntity) {
    HolidayResponse holidayToJSON = new HolidayResponse();
    holidayToJSON.setName(holidayEntity.getName());

    Date date = new Date();
    date.setIso(holidayEntity.getDate().toString());

    holidayToJSON.setDate(date);

    holidayToJSON.setHolidayType(List.of(holidayEntity.getHolidayType()));
    holidayToJSON.setDescription(holidayEntity.getDescription());

    logger.info("Holiday entity has been mapped to response");

    return holidayToJSON;
  }
}

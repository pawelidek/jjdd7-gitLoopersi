package com.infoshareacademy.gitloopersi.mapper;

import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import com.infoshareacademy.gitloopersi.entity.Holiday;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class HolidayMapper {

  public List<Holiday> mapApiToEntity(List<HolidayApi> holidayApiList) {
    List<Holiday> holidays = new ArrayList<>();

    holidayApiList.forEach(holidayApi -> {
      Holiday holiday = new Holiday();
      holiday.setName(holidayApi.getName());
      holiday.setDate(convertToDate(holidayApi.getDate().getIso()));
      holiday.setDescription(holidayApi.getDescription());
      holiday.setTypeOfHoliday(holidayApi.getHolidayType().get(0));
    });
    return holidays;
  }

  private LocalDate convertToDate(String dateToConvert) {
    return LocalDate.parse(dateToConvert);
  }
}

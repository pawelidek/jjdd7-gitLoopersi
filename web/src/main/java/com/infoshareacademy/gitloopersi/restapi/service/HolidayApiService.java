package com.infoshareacademy.gitloopersi.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.service.HolidayService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Stateless
public class HolidayApiService {

  @EJB
  private HolidayService holidayService;

  @EJB
  private HolidayMapper holidayMapper;

  @Transactional
  public String getHolidayJsonObjectById(int id) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    Holiday holidayById = holidayService.findHolidayById(id);

    return objectMapper.writeValueAsString(holidayMapper.mapEntityToApi(holidayById));
  }
}

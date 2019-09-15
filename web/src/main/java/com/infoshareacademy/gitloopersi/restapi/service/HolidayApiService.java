package com.infoshareacademy.gitloopersi.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.api.HolidayResponse;
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
  public HolidayResponse getHolidayJsonObjectById(Integer id) throws JsonProcessingException {
    return holidayMapper.mapEntityToApi(holidayService.findHolidayById(id));
  }
}
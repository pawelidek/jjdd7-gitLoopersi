package com.infoshareacademy.gitloopersi.service.apimanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoshareacademy.gitloopersi.domain.jsonapi.HolidayResponse;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.web.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.service.holidaymanager.HolidayService;
import java.util.ArrayList;
import java.util.List;
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

  @Transactional
  public List<HolidayResponse> getHolidayJsonObjectsByPattern(String param)
      throws JsonProcessingException {
    List<Holiday> foundHolidaysByPattern = holidayService
        .findHolidaysByPattern(param.toLowerCase());
    List<HolidayResponse> holidayReponses = new ArrayList<>();
    foundHolidaysByPattern
        .forEach(holidayEntity -> holidayReponses.add(holidayMapper.mapEntityToApi(holidayEntity)));
    return holidayReponses;
  }
}
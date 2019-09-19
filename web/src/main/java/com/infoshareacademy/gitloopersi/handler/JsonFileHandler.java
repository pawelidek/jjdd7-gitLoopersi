package com.infoshareacademy.gitloopersi.handler;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.domain.jsonapi.HolidayResponse;
import com.infoshareacademy.gitloopersi.exception.HolidaysFileNotFound;
import com.infoshareacademy.gitloopersi.service.apimanager.ParserService;
import com.infoshareacademy.gitloopersi.service.filemanager.FileUploadProcessor;
import com.infoshareacademy.gitloopersi.web.mapper.HolidayMapper;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class JsonFileHandler {

  @Inject
  private FileUploadProcessor fileUploadProcessor;

  @Inject
  private ParserService parserService;

  @EJB
  private HolidayMapper holidayMapper;

  @EJB
  private HolidayDaoBean holidayDaoBean;

  public void prepareFileToLoadToDataBase(Part filePart) {

    Logger logger = LoggerFactory.getLogger(getClass().getName());
    try {
      List<HolidayResponse> holidayList = parserService
          .parseHolidays(fileUploadProcessor.uploadHolidaysFile(filePart));
      List<Holiday> holidays = holidayMapper.mapApiToEntity(
          holidayList);
      holidays.forEach(holiday -> holidayDaoBean.addHoliday(holiday));
    } catch (IOException | HolidaysFileNotFound e) {
      logger.error(e.getMessage());
    }
  }
}
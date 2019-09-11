package com.infoshareacademy.gitloopersi.handler;

import com.infoshareacademy.gitloopersi.dao.ApiHolidayDao;
import com.infoshareacademy.gitloopersi.domain.api.HolidayApi;
import com.infoshareacademy.gitloopersi.domain.entity.Holiday;
import com.infoshareacademy.gitloopersi.exception.HolidaysFileNotFound;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.parser.Parser;
import com.infoshareacademy.gitloopersi.service.FileUploadProcessor;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class JsonFileHandler {

  @Inject
  private FileUploadProcessor fileUploadProcessor;

  @Inject
  private Parser parser;

  @EJB
  private HolidayMapper holidayMapper;

  @EJB
  private ApiHolidayDao apiHolidayDao;

  public void prepareFileToLoadToDataBase(Part filePart) {

    Logger logger = LoggerFactory.getLogger(getClass().getName());
    try {
      List<HolidayApi> holidayApiList = parser
          .parseHolidays(fileUploadProcessor.uploadHolidaysFile(filePart));
      List<Holiday> holidays = holidayMapper.mapApiToEntity(holidayApiList);
      holidays.forEach(holiday -> apiHolidayDao.addHoliday(holiday));
    } catch (IOException | HolidaysFileNotFound e) {
      logger.error(e.getMessage());
    }
  }
}
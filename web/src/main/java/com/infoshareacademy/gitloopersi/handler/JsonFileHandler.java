package com.infoshareacademy.gitloopersi.handler;

import com.infoshareacademy.gitloopersi.dao.HolidayDaoBean;
import com.infoshareacademy.gitloopersi.domain.api.Holiday;
import com.infoshareacademy.gitloopersi.exception.HolidaysFileNotFound;
import com.infoshareacademy.gitloopersi.mapper.HolidayMapper;
import com.infoshareacademy.gitloopersi.parser.Parser;
import com.infoshareacademy.gitloopersi.service.FileUploadProcessor;
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
  private Parser parser;

  @EJB
  private HolidayMapper holidayMapper;

  @EJB
  private HolidayDaoBean holidayDaoBean;

  public void prepareFileToLoadToDataBase(Part filePart) {

    Logger logger = LoggerFactory.getLogger(getClass().getName());
    try {
      List<Holiday> holidayList = parser
          .parseHolidays(fileUploadProcessor.uploadHolidaysFile(filePart));
      List<com.infoshareacademy.gitloopersi.domain.entity.Holiday> holidays = holidayMapper.mapApiToEntity(
          holidayList);
      holidays.forEach(holiday -> holidayDaoBean.addHoliday(holiday));
    } catch (IOException | HolidaysFileNotFound e) {
      logger.error(e.getMessage());
    }
  }
}

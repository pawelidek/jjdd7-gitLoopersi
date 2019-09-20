package com.infoshareacademy.gitloopersi.service.vacationmanager;

import com.infoshareacademy.gitloopersi.dao.VacationDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.exception.DatesOverlapException;
import com.infoshareacademy.gitloopersi.exception.VacationOutOfPoolException;
import com.infoshareacademy.gitloopersi.service.employeemanager.EmployeeService;
import com.infoshareacademy.gitloopersi.validator.VacationDefiningValidator;
import com.infoshareacademy.gitloopersi.web.mapper.VacationViewMapper;
import com.infoshareacademy.gitloopersi.web.view.VacationView;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class VacationDefiningService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private VacationDaoBean vacationDefiningDao;

  @EJB
  private EmployeeService employeeService;

  @EJB
  private VacationViewMapper vacationViewMapper;

  @Inject
  private VacationDefiningValidator vacationDefiningValidator;

  @Transactional
  public void addVacation(Vacation vacation, Long employeeId) {
    logger.info("New vacation object id = [{}] go to DAO to be saved in DB", vacation.getId());
    Employee employee = employeeService.getEmployeeById(employeeId);
    vacation.setEmployee(employee);
    vacationDefiningDao.addVacation(vacation);
  }

  public void deleteVacation(Long vacationId) {
    logger.info("Vacation object id={} go to DAO to be removed in DB", vacationId);
    vacationDefiningDao.deleteVacation(vacationId);
  }

  public List<Vacation> getVacationsList() {
    logger.info("Objects vacation go to DAO to be found in DB");
    return vacationDefiningDao.getVacationsList();
  }

  public List<VacationView> getVacationsWithEmployeesList() {
    List<VacationView> vacationViews = new ArrayList<>();

    getVacationsList().forEach(e -> {
      vacationViews.add(vacationViewMapper.mapEntityToView(e));
    });

    return vacationViews;
  }

  public boolean isValidVacationRequestByEmployee(Long employeeId, String dateFrom, String dateTo)
      throws VacationOutOfPoolException, DatesOverlapException {

    if (isValidOverlappingOfDates(employeeId, dateFrom, dateTo)) {

      int numberOfVacationPool = getNumberOfVacationPool(employeeId);

      int numberOfSelectedVacationDays = getNumberOfSelectedVacationDays(dateFrom, dateTo);

      int numberOfRemainingVacationDays = getNumberOfRemainingVacationDays(employeeId,
          numberOfVacationPool, numberOfSelectedVacationDays);

      if (numberOfRemainingVacationDays > 0) {
        return true;
      } else {
        logger.warn("Number of remaining vacation days is {}", numberOfRemainingVacationDays);
        throw new VacationOutOfPoolException("Number of remaining vacation days is not sufficient");
      }
    } else {
      logger.warn("Dates overlap with holidays already notified {} - {}", dateFrom, dateTo);
      throw new DatesOverlapException("Dates overlap with vacations already notified");
    }
  }

  private int getNumberOfRemainingVacationDays(Long employeeId, int numberOfVacationPool,
      int numberOfSelectedVacationDays) {

    return vacationDefiningValidator
        .calculateRemainingVacationPool(employeeId, numberOfSelectedVacationDays,
            numberOfVacationPool);
  }

  public int getNumberOfSelectedVacationDays(String dateFrom, String dateTo) {

    return vacationDefiningValidator
        .calculateNumberOfSelectedVacationDays(dateFrom, dateTo);
  }

  private int getNumberOfVacationPool(Long employeeId) {

    return vacationDefiningValidator
        .calculateVacationPoolForEmployee(employeeId);
  }

  private boolean isValidOverlappingOfDates(Long employeeId, String dateFrom, String dateTo) {

    return vacationDefiningValidator.isValidOverlappingOfDates(employeeId, dateFrom, dateTo);
  }
}
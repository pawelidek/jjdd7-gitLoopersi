package com.infoshareacademy.gitloopersi.web.controller;

import com.infoshareacademy.gitloopersi.domain.entity.Vacation;
import com.infoshareacademy.gitloopersi.service.vacationmanager.VacationDefiningService;
import com.infoshareacademy.gitloopersi.types.StatusType;
import javax.ejb.EJB;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin/vacation")
public class AdminVacationController {

  @EJB
  private VacationDefiningService vacationDefiningService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @PUT
  @Path("/reject/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response rejectVacation(@PathParam("id") Long id) {
    logger.info("Api vacation editing: {}", id);

    Vacation vacation = vacationDefiningService.getByVacationId(id);

    vacation.setStatusType(StatusType.REJECTED);

    vacationDefiningService.editVacation(vacation);

    return Response.ok().build();
  }

  @PUT
  @Path("/accept/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response acceptVacation(@PathParam("id") Long id) {
    logger.info("Api vacation editing: {}", id);

    Vacation vacation = vacationDefiningService.getByVacationId(id);

    vacation.setStatusType(StatusType.ACCEPTED);

    vacationDefiningService.editVacation(vacation);

    return Response.ok().build();
  }
}

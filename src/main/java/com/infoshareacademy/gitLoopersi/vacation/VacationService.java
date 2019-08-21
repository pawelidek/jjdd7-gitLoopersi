package com.infoshareacademy.gitLoopersi.vacation;

import com.infoshareacademy.gitLoopersi.deserialization.Deserializator;
import com.infoshareacademy.gitLoopersi.domain.Vacation;
import com.infoshareacademy.gitLoopersi.repository.VacationRepository;
import com.infoshareacademy.gitLoopersi.serialization.Serializator;
import java.util.List;

public class VacationService {

  private Serializator serializator;
  private Deserializator deserializator;
  private static final String fileName = "vacation.ser";

  public VacationService() {
    serializator = new Serializator();
    deserializator = new Deserializator();
  }

  public void loadVacationData() {
    List<Vacation> vacations = deserializator.deserialize(new Vacation(), fileName);
    VacationRepository.getAllVacations().clear();
    VacationRepository.getAllVacations()
        .addAll(vacations);
  }

  public void addVacation(List<Vacation> vacations) {
    VacationRepository.setAllVacations(vacations);
    System.out.println(vacations.get(0));
    serializator.serialize(VacationRepository.getAllVacations(), getFileName());
  }

  public static String getFileName() {
    return fileName;
  }
}

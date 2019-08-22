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

  void addVacation(List<Vacation> vacations) {
    VacationRepository.setAllVacations(vacations);
    serializator.serialize(VacationRepository.getAllVacations(), getFileName());
    System.out.println("\nProcedure of defining vacation successfully finished.");
    System.out.println("\nType '0' to return or 'Enter' to add define vacation.");
  }

  void cancelVacation(Vacation vacation) {
    VacationRepository.getAllVacations().remove(vacation);
    serializator.serialize(VacationRepository.getAllVacations(), getFileName());
    System.out.println("\nVacation has been successfully cancelled.");
    System.out.println("\nType '0' to return or 'Enter' to cancel vacation.");
  }

  public static String getFileName() {
    return fileName;
  }
}

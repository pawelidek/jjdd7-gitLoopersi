package com.infoshareacademy.gitLoopersi.vacation;

import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeVacationSearcher {

  public void searchForEmployee() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter at least three signs of searched employee name: ");
    String searchedPhrase = scanner.nextLine();
    Integer signs = searchedPhrase.length();

    while (signs < 3) {
      System.out.println("Please enter at least three signs! Enter searched phrase: ");
      searchedPhrase = scanner.nextLine();
      signs = searchedPhrase.length();
    }



    List<Employee> lista = new ArrayList<>();

    String finalSearchedPhrase = searchedPhrase;
    lista = EmployeeRepository.getAllEmployees()
        .stream()
        .filter(e -> (e.getFirstName().concat(" " + e.getSecondName())).contains(finalSearchedPhrase))
        .collect(Collectors.toList());
    System.out.println(lista);
  }
}

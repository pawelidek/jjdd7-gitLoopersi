package com.infoshareacademy.gitloopersi.exception;

public class TeamNotEmptyException extends Exception {

  public TeamNotEmptyException() {

  }

  public TeamNotEmptyException(String errorMessage) {
    super(errorMessage);
  }
}

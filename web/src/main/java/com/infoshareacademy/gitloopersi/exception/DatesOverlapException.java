package com.infoshareacademy.gitloopersi.exception;

public class DatesOverlapException extends Exception {

  public DatesOverlapException() {
  }

  public DatesOverlapException(String errorMessage) {
    super(errorMessage);
  }
}

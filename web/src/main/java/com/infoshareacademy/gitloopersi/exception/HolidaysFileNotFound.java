package com.infoshareacademy.gitloopersi.exception;

public class HolidaysFileNotFound extends Exception {

  public HolidaysFileNotFound() {

  }

  public HolidaysFileNotFound(String errorMessage) {
    super(errorMessage);
  }
}

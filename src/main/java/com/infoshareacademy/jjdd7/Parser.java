package com.infoshareacademy.jjdd7;

import java.util.ArrayList;

public interface Parser {
    void createTextJSON();
    void createJSONObject();
    void loadDateToArray();
    ArrayList<Holiday> getListOfHolidays();
}

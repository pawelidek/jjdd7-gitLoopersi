package com.infoshareacademy.jjdd7.menu.menufunctions;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.search.HolidaySearcher;

import java.util.Scanner;

public class HolidayNameCapturer implements Menu {

    private HolidaySearcher holidaySearcher = new HolidaySearcher();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void doAction() {
        holidaySearcher.searchHolidayByName(scanner);
    }
}
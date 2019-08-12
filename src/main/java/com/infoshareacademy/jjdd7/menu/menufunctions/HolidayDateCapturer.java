package com.infoshareacademy.jjdd7.menu.menufunctions;

import com.infoshareacademy.jjdd7.menu.Menu;
import com.infoshareacademy.jjdd7.search.FreeDaySearcher;

import java.util.Scanner;

public class HolidayDateCapturer implements Menu {

    private FreeDaySearcher freeDaySearcher = new FreeDaySearcher();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void doAction() {
        freeDaySearcher.searchFreeDay(scanner);
    }
}
package com.infoshareacademy.jjdd7.menu;

import java.util.HashMap;
import java.util.Map;

public class MenuTree {

    public void buildMenuTree() {
        Map<String, Menu> map = new HashMap<>();
        PrintTitle printTitle = new PrintTitle();
        map.put("a", printTitle.doAction());
    }
}

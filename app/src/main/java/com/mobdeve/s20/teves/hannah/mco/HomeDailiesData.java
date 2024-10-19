package com.mobdeve.s20.teves.hannah.mco;

import java.util.ArrayList;
import java.util.List;

public class HomeDailiesData {
    String time;
    String task;

    HomeDailiesData(String time, String task) {
        this.time = time;
        this.task = task;
    }

    // Sample data
    public static List<HomeDailiesData> getData() {
        List<HomeDailiesData> list = new ArrayList<>();
        list.add(new HomeDailiesData("12:30 PM", "Complete your Daily Commissions!"));
        list.add(new HomeDailiesData("12:30 PM", "Farm for artifacts!"));
        list.add(new HomeDailiesData("12:30 PM", "Don't forget to use your Resins!"));
        return list;
    }

    public static void addTask(List<HomeDailiesData> list, String time, String task) {
        list.add(new HomeDailiesData(time, task));
    }
}

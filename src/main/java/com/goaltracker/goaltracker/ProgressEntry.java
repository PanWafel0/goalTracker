package com.goaltracker.goaltracker;

import java.time.LocalDate;

public class ProgressEntry {
    private int id;
    private int value;
    private LocalDate date;

    public ProgressEntry(int id, int value, LocalDate date){
        this.id = id;
        this.value = value;
        this.date = date;
    }
}


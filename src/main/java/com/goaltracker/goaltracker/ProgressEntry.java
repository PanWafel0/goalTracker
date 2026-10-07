package com.goaltracker.goaltracker;

import java.time.LocalDate;

public class ProgressEntry {
    private int id;
    private double value;
    private LocalDate date;

    public ProgressEntry( double value, LocalDate date){
        this.value = value;
        this.date = date;
    }
    public int getId(){
        return id;
    }
    public double getValue(){
        return value;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setValue(double newValue){
        value = newValue;
    }
    public void setDate(LocalDate newDate){
        date = newDate;
    }
    public void setId(int id) {
        this.id =id;
    }
}


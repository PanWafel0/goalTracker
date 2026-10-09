package com.goaltracker.goaltracker;

import java.time.LocalDate;

public class ProgressEntry {
    private int id;
    private double value;
    private LocalDate date;
    private boolean done;

    public ProgressEntry( double value, LocalDate date){
        this.value = value;
        this.date = date;
        this.done = false;
    }
    public ProgressEntry( boolean done, LocalDate date){
        this.value = 0;
        this.date = date;
        this.done = done;
    }
    //gettery
    public int getId(){
        return id;
    }
    public double getValue(){
        return value;
    }
    public LocalDate getDate(){
        return date;
    }
    public boolean getDone(){
        return done;
    }

    //settery
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


package com.goaltracker.goaltracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Goal {
    private int id;
    private String name;
    private GoalType type;
    private String goalUnit;
    private double targetValue;
    private Category category;
    private String status;
    private boolean isDeleted;
    private List<ProgressEntry> entries;
    private int currentEntryId=1;


    public Goal(String name, GoalType type,String goalUnit, double targetValue, Category category){
        this.name = name;
        this.type = type;
        this.goalUnit = goalUnit;
        this.targetValue = targetValue;
        this.category = category;
        this.isDeleted = false;
        this.entries = new ArrayList<>();
        this.status = "W trakcie";
    }
    //gettery
    public List<ProgressEntry> getEntries(){ return entries;}
    public int getId() {return id;}
    public String getName(){return name;}
    public GoalType getType(){return type;}
    public String getGoalUnit(){return goalUnit;}
    public double getTargetValue(){return targetValue;}
    public Category getCategory(){return category;}
    public String getStatus(){return status;}
    public int  getCurrentEntryId(){return currentEntryId;}
    public ProgressEntry getLastEntry(){
        return entries.getLast();
    }

    public double getCompletionPercentage(){
        if(type==GoalType.NUMERIC) {
            double total = 0;
            for (ProgressEntry entry : entries) {
                total += entry.getValue();
            }
            double percentage = Math.floor(total/targetValue*100);
            if(percentage>100){
                return 100.0;
            }
            return percentage;
        }
        return 0.0;
    }
    //settery
    public void setDeleted (boolean isDeleted){this.isDeleted = isDeleted;}

    private void checkStatus(){
        if(getCompletionPercentage()<100){
            status = "W trakcie";
        }
        else{
            status = "Zakończony";
        }
    }


    public boolean isDeleted(){return isDeleted;}

    //zarządzanie wpisami postępu
    public boolean addEntry(ProgressEntry entry){
        if(entry.getValue()<=0){
            System.out.println("Nie można dodac wpisu z wartością nie wiekszą niż 0");
            return false;
        }
        if(entry.getDate().isAfter(LocalDate.now())){
            System.out.println("data nie może wskazywać przyszłosci");
            return false;
        }
        if("W trakcie".equals(status)){
            entries.add(entry);
            entry.setId(currentEntryId++);
            checkStatus();
            return true;
        }
        else{
            System.out.println("status: "+status);
            System.out.println("nie można dodać wpisu");
            return false;
        }
    }
    public boolean deleteEntryById(int entryId){
        int i =0;
        while(i< entries.size()){
            if(entries.get(i).getId() == entryId){
                entries.remove(i);
                checkStatus();
                return true;
            }
            i++;
        }
        return false;
    }

    public void setId(int id) {
        this.id =id;
    }
    public boolean isFinished(){
        return getStatus().equals("Zakończony");
    }
}
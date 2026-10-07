package com.goaltracker.goaltracker;

import java.util.ArrayList;
import java.util.List;

public class Goal {
    private int id;
    private String name;
    private GoalType type;
    private double targetValue;
    private Category category;
    private String status;
    private boolean isDeleted;
    private List<ProgressEntry> entries;
    private int currentEntryId=1;


    public Goal(String name, GoalType type, double targetValue, Category category){
        this.name = name;
        this.type = type;
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
    public double getTargetValue(){return targetValue;}
    public Category getCategory(){return category;}
    public String getStatus(){return status;}
    public double getCompletionPercentage(){
        if(type==GoalType.NUMERIC) {
            double total = 0;
            for (ProgressEntry entry : entries) {
                total += entry.getValue();
            }
            double percentage = total/targetValue*100;
            if(percentage>100){
                return 100.0;
            }
            return percentage;
        }
        return 0.0;
    }
    //settery
    public void setDeleted (boolean isDeleted){this.isDeleted = isDeleted;}

    public boolean isDeleted(){return isDeleted;}

    //zarządzanie wpisami postępu
    public void addEntry(ProgressEntry entry){
        if(status=="W trakcie") {
            entries.add(entry);
            entry.setId(currentEntryId++);
            if (type == GoalType.NUMERIC && getCompletionPercentage() == 100.0) {
                status = "Zakończony";
            }
        }
        else{
            System.out.println("status: "+status);
            System.out.println("nie można dodać wpisu");
        }
    }
    public void removeEntryById(int entryId){
        int i =0;
        while(i< entries.size()){
            if(entries.get(i).getId() == entryId){
                entries.remove(i);
                return;
            }
            i++;
        }
    }

    public void setId(int id) {
        this.id =id;
    }
}
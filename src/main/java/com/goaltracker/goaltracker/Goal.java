package com.goaltracker.goaltracker;

import java.util.ArrayList;
import java.util.List;

public class Goal {
    private int id;
    private String name;
    private GoalType type;
    private double targetValue;
    private Category category;
    private boolean isDeleted;
    private List<ProgressEntry> entries;

    public Goal(int id, String name, GoalType type, double targetValue, Category category){
        this.id = id;
        this.name = name;
        this.type = type;
        this.targetValue = targetValue;
        this.category = category;
        this.isDeleted = false;
        this.entries = new ArrayList<>();
    }

    public List<ProgressEntry> getEntries(){ return entries;}
    public int getId() {return id;}
    public String getName(){return name;}
    public GoalType getType(){return type;}
    public double getTargetValue(){return targetValue;}
    public Category getCategory(){return category;}
    public boolean isDeleted(){return isDeleted;}

    public void setDeleted (boolean isDeleted){this.isDeleted = isDeleted;}

    public void addEntry(ProgressEntry entry){entries.add(entry);}
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
}
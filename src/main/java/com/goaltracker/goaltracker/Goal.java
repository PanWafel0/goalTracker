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
    private double getTotal(){
        double total = 0;
        for (ProgressEntry entry : entries){
            total+=entry.getValue();
        }
        return total;
    }

    public boolean isFinished(){
        return getStatus().equals("Zakończony");
    }

    public double getCompletionPercentage(){
        if(type==GoalType.NUMERIC) {
            double percentage = Math.floor(getTotal()/targetValue*100);
            if(percentage>100){
                return 100.0;
            }
            return percentage;
        }
        return 0.0;
    }

    public int getStreak(){
        LocalDate todayDate = LocalDate.now();
        LocalDate yesterdayDate = todayDate.minusDays(1);
        int streak = 0;
        LocalDate currentDay;
        if(hasEntryOnDate(todayDate)){
            currentDay = todayDate;
        } else if (hasEntryOnDate(yesterdayDate)) {
            currentDay = yesterdayDate;
        }
        else return 0;
        while(hasEntryOnDate(currentDay)){
            streak++;
            currentDay = currentDay.minusDays(1);
        }

        return streak;
    }
    //
    private boolean hasEntryOnDate(LocalDate day){
        for(ProgressEntry entry : entries){
            if(entry.getDate().equals(day)){
                return true;
            }
        }
        return false;
    }
    //settery
    public void setDeleted (boolean isDeleted){this.isDeleted = isDeleted;}

    public void setId(int id) {
        this.id =id;
    }

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
        if(entry.getValue()<=0 && type == GoalType.NUMERIC){
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

    public boolean changeEntryValue(int entryId, double newValue){
        int i=0;
        if (newValue<=0){
            return false;
        }
        while(i<entries.size()){
            if (entries.get(i).getId() == entryId) {
                entries.get(i).setValue(newValue);
                checkStatus();
                return true;
            }
            i++;
        }
        return false;
    }
    public boolean changeTargetValue(double newValue){
        if(!isFinished() || newValue <= 0){
            return false;
        }
        this.targetValue = newValue;
        checkStatus();
        return true;
    }
}
package com.goaltracker.goaltracker;

import java.util.ArrayList;
import java.util.List;

public class GoalService {
    private List<Goal> goals;
    private int currentId=1;

    public GoalService(){
        this.goals = new ArrayList<>();
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public boolean addGoal(Goal goal){
        if(isValid(goal)){
            goals.add(goal);
            goal.setId(currentId++);
            return true;
        }
        return false;
    }


    public Goal findGoalById(int id){
        for(Goal goal : goals){
            if(goal.getId() == id){
                return goal;
            }
        }
        return null;
    }
    //zarzadzanie entry
    public boolean addEntryToGoal(int goalId, ProgressEntry entry){
        Goal goal = findGoalById(goalId);

        if(goal == null){
            return false;
        }
        return goal.addEntry(entry);
    }
    public boolean deleteEntryFromGoal(int entryId, int goalId){
        Goal goal = findGoalById(goalId);
        if(goal == null){
            return false;
        }
        return goal.deleteEntryById(entryId);
    }

    //usuwanie i przywracanie celów
    public boolean moveGoalToTrashById(int id){
        Goal goal = findGoalById(id);
        if(goal != null){
            goal.setDeleted(true);
            return true;
        }
        return false;
    }
    public boolean deleteGoalPermanentlyById(int goalId){
        Goal goal = findGoalById(goalId);
        if(goal != null && goal.isDeleted()){
            goals.remove(goal);
            return true;
        }
        return false;
    }
    public boolean restoreGoalFromTrashById(int id){
        Goal goal = findGoalById(id);
        if(goal != null){
            goal.setDeleted(false);
            return true;
        }
        return false;
    }

    //pokazywanie listy oraz listy kosza
    private void showAllGoals(Goal goal){
            System.out.print("[" + goal.getId() + "][");
            System.out.print(goal.getName() + " | ");
            System.out.print(goal.getTargetValue() + " | ");
            System.out.print(goal.getCompletionPercentage() + "% | ");
            System.out.print(goal.getGoalUnit() + " | ");
            System.out.print(goal.getStatus() + " | ");
            System.out.print(goal.getCategory() + " | ");
            System.out.print(goal.getType() + "]");
            if (!goal.getEntries().isEmpty()) {
                ProgressEntry lastEntry = goal.getLastEntry();
                System.out.print(" Ostatni wpis: [" + lastEntry.getValue() + "]");
            } else {
                System.out.print(" Brak wpisów");
            }

            System.out.println();
    }
    public void showList(){
        System.out.println("Lista celów: ");
        for(Goal goal : goals){
            if(!goal.isDeleted()) {
                showAllGoals(goal);
            }
        }
    }
    public void showGoalsInTrash(){
        System.out.println("Lista usuniętych celów: ");
        for(Goal goal : goals){
            if(goal.isDeleted()) {
                showAllGoals(goal);
            }
        }
    }

    private boolean isValid(Goal goal) {
        // US 1.1
        if (goal.getName() == null || goal.getName().isBlank()) {
            return false;
        }

        // US 1.2
        if (goal.getType() == null) {
            return false;
        }

        switch (goal.getType()) {
            case HABIT, NUMERIC -> {
            }
            default -> {
                return false;
            }
        }

        // US 1.3
        if (goal.getType() == GoalType.NUMERIC && goal.getTargetValue() <= 0) {
            return false;
        }

        // US 1.4
        if (goal.getCategory() == null) {
            return false;
        }

        switch (goal.getCategory()) {
            case EDUCATION, HEALTH, HOBBY, SPORT, OTHER -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }


}

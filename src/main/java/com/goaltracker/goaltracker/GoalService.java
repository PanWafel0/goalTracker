package com.goaltracker.goaltracker;

import java.util.ArrayList;
import java.util.List;

public class GoalService {
    private List<Goal> goals;
    private int currentId=1;

    public GoalService(){
        this.goals = new ArrayList<>();
    }

    public void showList(){
        for(Goal goal : goals){
            if(!goal.isDeleted()){
                System.out.println(goal.getName());
            }
        }
    }

    public boolean addGoal(Goal goal){
        if(isValid(goal)){
            goals.add(goal);
            goal.setId(currentId++);
            return true;
        }
        return false;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public Goal findGoalById(int id){
        for(Goal goal : goals){
            if(goal.getId() == id){
                return goal;
            }
        }
        return null;
    }
    public void moveGoalToTrashById(int id){
        Goal goal = findGoalById(id);
        if(goal != null){
            goal.setDeleted(true);
        }
    }

    public void deleteGoalPermanentlyById(int id){
        Goal goal = findGoalById(id);
        if(goal != null){
            goals.remove(goal);
        }
    }

    public void restoreGoalFromTrashById(int id){
        Goal goal = findGoalById(id);
        if(goal != null){
            goal.setDeleted(false);
        }
    }

    public void showGoalsInTrash(){
        for(Goal goal : goals){
            if(goal.isDeleted()){
                System.out.println(goal.getName());
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

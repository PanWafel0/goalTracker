package com.goaltracker.goaltracker;

import java.util.ArrayList;
import java.util.List;

public class GoalService {
    private List<Goal> goals;

    public GoalService(){
        this.goals = new ArrayList<>();
    }

    public void showList(){
        for(Goal goal : goals){
            System.out.println(goal.getName());
        }
    }

    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public List<Goal> getGoals() {
        return goals;
    }

}

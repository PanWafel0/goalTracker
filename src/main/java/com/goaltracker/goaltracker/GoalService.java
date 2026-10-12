package com.goaltracker.goaltracker;

import java.util.List;

public class GoalService {
    private final List<Goal> goals;
    private int currentId = 1;
    private final GoalStorage storage = new GoalStorage();

    public GoalService() {
        this.goals = storage.loadGoals();
        recalculateIdCounter();
    }

    private void recalculateIdCounter() {
        int maxId = 0;
        for (Goal goal : goals) {
            if (goal.getId() > maxId) {
                maxId = goal.getId();
            }
        }
        currentId = maxId + 1;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    private void persist() {
        storage.saveGoals(goals);
    }

    public boolean addGoal(Goal goal) {
        if (isValid(goal)) {
            goals.add(goal);
            goal.setId(currentId++);
            persist();
            return true;
        }
        return false;
    }

    public Goal findGoalById(int id) {
        for (Goal goal : goals) {
            if (goal.getId() == id) {
                return goal;
            }
        }
        return null;
    }

    private String getPolishCategoryName(Category category) {
        switch (category) {
            case SPORT:
                return "SPORT";
            case EDUCATION:
                return "EDUKACJA";
            case HEALTH:
                return "ZDROWIE";
            case HOBBY:
                return "HOBBY";
            case OTHER:
                return "INNE";
            default:
                return "NIEZNANA";
        }
    }

    //zarzadzanie entry
    public boolean addEntryToGoal(int goalId, ProgressEntry entry) {
        Goal goal = findGoalById(goalId);

        if (goal == null) {
            return false;
        }
        boolean result = goal.addEntry(entry);
        if (result) {
            persist();
        }
        return result;
    }

    public boolean deleteEntryFromGoal(int entryId, int goalId) {
        Goal goal = findGoalById(goalId);
        if (goal == null) {
            return false;
        }
        boolean result = goal.deleteEntryById(entryId);
        if (result) {
            persist();
        }
        return result;
    }

    //usuwanie i przywracanie celów
    public boolean moveGoalToTrashById(int id) {
        Goal goal = findGoalById(id);
        if (goal != null) {
            goal.setDeleted(true);
            persist();
            return true;
        }
        return false;
    }

    public boolean deleteGoalPermanentlyById(int goalId) {
        Goal goal = findGoalById(goalId);
        if (goal != null && goal.isDeleted()) {
            goals.remove(goal);
            persist();
            return true;
        }
        return false;
    }

    public boolean restoreGoalFromTrashById(int id) {
        Goal goal = findGoalById(id);
        if (goal != null && goal.isDeleted()) {
            goal.setDeleted(false);
            persist();
            return true;
        }
        return false;
    }

    //pokazywanie listy oraz listy kosza
    private void showAllGoals(Goal goal) {
        boolean isHabit = goal.getType() == GoalType.HABIT;
        System.out.print("[" + goal.getId() + "][");
        System.out.print(goal.getName() + " | ");
        if (goal.getType() == GoalType.NUMERIC) {
            System.out.print(goal.getTargetValue() + " | ");
            System.out.print(goal.getCompletionPercentage() + "% | ");
            System.out.print(goal.getGoalUnit() + " | ");
            System.out.print("Status[ " + goal.getStatus() + " ] | ");
        }
        System.out.print("Streak[" + goal.getStreak() + "] | ");
        System.out.print(goal.getCategory() + " | ");
        System.out.print(goal.getType() + "]");
        if (!goal.getEntries().isEmpty()) {
            ProgressEntry lastEntry = goal.getLastEntry();
            System.out.print(" Ostatni wpis: [" + (isHabit ? (lastEntry.getDone() ? "tak" : "nie") : lastEntry.getValue()) + "]");
        } else {
            System.out.print(" Brak wpisów");
        }

        System.out.println();
    }

    public void showList() {
        System.out.println("Lista celów: ");
        for (Goal goal : goals) {
            if (!goal.isDeleted()) {
                showAllGoals(goal);
            }
        }
    }

    public void showGoalsInTrash() {
        System.out.println("Lista usuniętych celów: ");
        for (Goal goal : goals) {
            if (goal.isDeleted()) {
                showAllGoals(goal);
            }
        }
    }

    public void showGoalsByCategory(Category category) {
        System.out.println("Cele z kategorii: " + getPolishCategoryName(category));
        for (Goal goal : goals) {
            if (!goal.isDeleted() && goal.getCategory() == category) {
                showAllGoals(goal);
            }
        }
    }

    public boolean changeGoalTargetValue(int goalId, double newValue) {
        Goal goal = findGoalById(goalId);
        if (goal == null || goal.isDeleted()) {
            return false;
        }
        boolean result = goal.changeTargetValue(newValue);
        if (result) {
            persist();
        }
        return result;
    }

    public boolean changeEntryValue(int goalId, int entryId, double newValue) {
        Goal goal = findGoalById(goalId);
        if (goal == null || goal.isDeleted()) {
            return false;
        }
        boolean result = goal.changeEntryValue(entryId, newValue);
        if (result) {
            persist();
        }
        return result;
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
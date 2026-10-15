package com.goaltracker.goaltracker;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }



    public void addGoal(Goal goal) {
        if (isValid(goal)) {
            goalRepository.save(goal);
        }
    }

    public Goal findGoalById(int id) {
        return goalRepository.findById(id).orElse(null);
    }

    private String getPolishCategoryName(Category category) {
        return switch (category) {
            case SPORT -> "SPORT";
            case EDUCATION -> "EDUKACJA";
            case HEALTH -> "ZDROWIE";
            case HOBBY -> "HOBBY";
            case OTHER -> "INNE";
        };
    }

    public boolean addEntryToGoal(int goalId, ProgressEntry entry) {
        Goal goal = findGoalById(goalId);

        if (goal == null) {
            return false;
        }

        entry.setGoal(goal);

        boolean result = goal.addEntry(entry);
        if (result) {
            goalRepository.save(goal);
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
            goalRepository.save(goal);
        }
        return result;
    }


    public boolean moveGoalToTrashById(int id) {
        Goal goal = findGoalById(id);
        if (goal != null) {
            goal.setDeleted(true);
            goalRepository.save(goal);
            return true;
        }
        return false;
    }

    public boolean deleteGoalPermanentlyById(int goalId) {
        Goal goal = findGoalById(goalId);
        if (goal != null && goal.isDeleted()) {
            goalRepository.delete(goal);
            return true;
        }
        return false;
    }

    public boolean restoreGoalFromTrashById(int id) {
        Goal goal = findGoalById(id);
        if (goal != null && goal.isDeleted()) {
            goal.setDeleted(false);
            goalRepository.save(goal);
            return true;
        }
        return false;
    }


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
        for (Goal goal : goalRepository.findByIsDeletedFalse()) {
            showAllGoals(goal);
        }
    }

    public void showGoalsInTrash() {
        System.out.println("Lista usuniętych celów: ");
        for (Goal goal : goalRepository.findByIsDeletedTrue()) {
            showAllGoals(goal);
        }
    }

    public void showGoalsByCategory(Category category) {
        System.out.println("Cele z kategorii: " + getPolishCategoryName(category));
        for (Goal goal : goalRepository.findByCategoryAndIsDeletedFalse(category)) {
            showAllGoals(goal);
        }
    }

    public boolean changeGoalTargetValue(int goalId, double newValue) {
        Goal goal = findGoalById(goalId);
        if (goal == null || goal.isDeleted()) {
            return false;
        }
        boolean result = goal.changeTargetValue(newValue);
        if (result) {
            goalRepository.save(goal);
        }
        return result;
    }


    private boolean isValid(Goal goal) {
        if (goal.getName() == null || goal.getName().isBlank()) {
            return false;
        }

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

        if (goal.getType() == GoalType.NUMERIC && goal.getTargetValue() <= 0) {
            return false;
        }

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
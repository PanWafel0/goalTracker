package com.goaltracker.goaltracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalServiceTest {

    @Test
    void addGoalAddsThreeGoals() {
        GoalService service = new GoalService();

        service.addGoal(new Goal(1, "Bieg", null, 5, null));
        service.addGoal(new Goal(2, "Czytanie", null, 20, null));
        service.addGoal(new Goal(3, "Woda", null, 2.5, null));

        assertEquals(3, service.getGoals().size());
        service.showList();
    }
}
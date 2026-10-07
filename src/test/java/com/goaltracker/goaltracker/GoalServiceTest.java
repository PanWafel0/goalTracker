package com.goaltracker.goaltracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GoalServiceTest {

    private GoalService service;

    @BeforeEach
    void setUp() {
        service = new GoalService();
        service.addGoal(new Goal(1, "Bieg", null, 5, null));
        service.addGoal(new Goal(2, "Czytanie", null, 20, null));
        service.addGoal(new Goal(3, "Woda", null, 2.5, null));
    }

    @Test
    void addGoalAddsThreeGoals() {
        assertEquals(3, service.getGoals().size());
    }

    @Test
    void addGoalIncreasesListSize() {
        service.addGoal(new Goal(4, "Spacer", null, 10, null));

        assertEquals(4, service.getGoals().size());
    }

    @Test
    void findGoalByIdReturnsCorrectGoal() {
        Goal found = service.findGoalById(2);

        assertEquals("Czytanie", found.getName());
    }

    @Test
    void findGoalByIdReturnsNullWhenMissing() {
        assertNull(service.findGoalById(99));
    }
}
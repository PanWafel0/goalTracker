package com.goaltracker.goaltracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalServiceTest {

    private GoalService service;

    @BeforeEach
    void setUp() {
        service = new GoalService();

        addGoal("Bieg", GoalType.NUMERIC, 5, Category.SPORT, true);
        addGoal("Czytanie", GoalType.NUMERIC, 20, Category.EDUCATION, true);
        addGoal("Woda", GoalType.NUMERIC, 2.5, Category.HEALTH, true);
    }

    @Test
    void shouldAddThreeGoals() {
        assertEquals(3, service.getGoals().size());
    }

    @Test
    void shouldAddValidGoal() {
        addGoal("Spacer", GoalType.NUMERIC, 10, Category.SPORT, true);

        assertEquals(4, service.getGoals().size());
    }

    @Test
    void shouldFindGoalById() {
        assertEquals("Czytanie", service.findGoalById(2).getName());
    }

    @Test
    void shouldReturnNullForMissingGoal() {
        assertNull(service.findGoalById(99));
    }

    @Test
    void shouldNotAddGoalWithBlankName() {
        addGoal("   ", GoalType.NUMERIC, 10, Category.SPORT, false);

        assertEquals(3, service.getGoals().size());
    }

    @Test
    void shouldNotAddNumericGoalWithZeroTargetValue() {
        addGoal("Nauka", GoalType.NUMERIC, 0, Category.EDUCATION, false);

        assertEquals(3, service.getGoals().size());
    }

    @Test
    void shouldAddHabitGoalWithZeroTargetValue() {
        addGoal("Codzienne czytanie", GoalType.HABIT, 0, Category.EDUCATION, true);

        assertEquals(4, service.getGoals().size());
    }

    private void addGoal(
            String name,
            GoalType type,
            double targetValue,
            Category category,
            boolean expected
    ) {
        boolean added = service.addGoal(new Goal(name, type, targetValue, category));

        assertEquals(expected, added);
    }
}
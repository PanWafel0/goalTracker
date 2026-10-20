package com.goaltracker.goaltracker.controller;

import com.goaltracker.goaltracker.Goal;
import com.goaltracker.goaltracker.GoalService;
import com.goaltracker.goaltracker.ProgressEntry;
import com.goaltracker.goaltracker.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public List<GoalResponseDto> getAllGoals() {
        List<Goal> goals = goalService.getGoals();
        List<GoalResponseDto> result = new ArrayList<>();

        for (Goal goal : goals) {
            GoalResponseDto dto = GoalMapper.toDto(goal);
            result.add(dto);
        }
        return result;
    }

    @PostMapping
    public GoalResponseDto addGoal(@RequestBody GoalRequestDto dto) {
        Goal goal = GoalMapper.toEntity(dto);
        goalService.addGoal(goal);
        return GoalMapper.toDto(goal);
    }

    @GetMapping("/trash")
    public List<GoalResponseDto> getGoalsInTrash() {
        List<Goal> goals = goalService.getGoalsInTrash();
        List<GoalResponseDto> result = new ArrayList<>();

        for (Goal goal : goals) {
            GoalResponseDto dto = GoalMapper.toDto(goal);
            result.add(dto);
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public String moveGoalToTrash(@PathVariable int id) {
        boolean result = goalService.moveGoalToTrashById(id);

        if (result) {
            return "Cel przeniesiony do kosza";
        } else {
            return "Nie znaleziono celu";
        }
    }

    @PutMapping("/{id}/restore")
    public String restoreGoal(@PathVariable int id) {
        boolean result = goalService.restoreGoalFromTrashById(id);

        if (result) {
            return "Cel przywrócony";
        } else {
            return "Nie znaleziono celu w koszu";
        }
    }

    @PostMapping("/{id}/entries")
    public String addEntry(@PathVariable int id, @RequestBody EntryRequestDto dto) {
        ProgressEntry entry = GoalMapper.toEntryEntity(dto);
        boolean result = goalService.addEntryToGoal(id, entry);

        if (result) {
            return "Wpis dodany";
        } else {
            return "Nie udało się dodać wpisu";
        }
    }

    @GetMapping("/{id}/entries")
    public List<EntryResponseDto> getEntries(@PathVariable int id) {
        Goal goal = goalService.findGoalById(id);

        if (goal == null) {
            return new ArrayList<>();
        }

        List<ProgressEntry> entries = goal.getEntries();
        List<EntryResponseDto> result = new ArrayList<>();

        for (ProgressEntry entry : entries) {
            EntryResponseDto dto = GoalMapper.toEntryDto(entry);
            result.add(dto);
        }
        return result;
    }
}
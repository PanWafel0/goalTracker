package com.goaltracker.goaltracker.controller;

import com.goaltracker.goaltracker.Goal;
import com.goaltracker.goaltracker.GoalService;
import com.goaltracker.goaltracker.dto.GoalMapper;
import com.goaltracker.goaltracker.dto.GoalRequestDto;
import com.goaltracker.goaltracker.dto.GoalResponseDto;
import org.springframework.web.bind.annotation.*;

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
        return goalService.getGoals().stream()
                .map(GoalMapper::toDto)
                .toList();
    }

    @PostMapping
    public GoalResponseDto addGoal(@RequestBody GoalRequestDto dto) {
        Goal goal = GoalMapper.toEntity(dto);
        goalService.addGoal(goal);
        return GoalMapper.toDto(goal);
    }
    @GetMapping("/trash")
    public List<GoalResponseDto> getGoalsInTrash() {
        return goalService.getGoalsInTrash().stream()
                .map(GoalMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public String moveGoalToTrash(@PathVariable int id) {
        boolean result = goalService.moveGoalToTrashById(id);
        return result ? "Cel przeniesiony do kosza" : "Nie znaleziono celu";
    }

    @PutMapping("/{id}/restore")
    public String restoreGoal(@PathVariable int id) {
        boolean result = goalService.restoreGoalFromTrashById(id);
        return result ? "Cel przywrócony" : "Nie znaleziono celu w koszu";
    }
}
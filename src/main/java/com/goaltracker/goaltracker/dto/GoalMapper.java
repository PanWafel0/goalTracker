package com.goaltracker.goaltracker.dto;

import com.goaltracker.goaltracker.Category;
import com.goaltracker.goaltracker.Goal;
import com.goaltracker.goaltracker.GoalType;

public class GoalMapper {

    public static Goal toEntity(GoalRequestDto dto) {
        return new Goal(
                dto.name(),
                GoalType.valueOf(dto.type()),
                dto.goalUnit(),
                dto.targetValue(),
                Category.valueOf(dto.category())
        );
    }
    public static GoalResponseDto toDto(Goal goal) {
        return new GoalResponseDto(
                goal.getId(),
                goal.getName(),
                goal.getType().name(),
                goal.getGoalUnit(),
                goal.getTargetValue(),
                goal.getCategory().name(),
                goal.getStatus(),
                goal.getCompletionPercentage(),
                goal.getStreak()
        );
    }
}
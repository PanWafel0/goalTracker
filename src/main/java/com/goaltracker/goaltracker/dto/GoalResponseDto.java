package com.goaltracker.goaltracker.dto;

public record GoalResponseDto(
        int id,
        String name,
        String type,
        String goalUnit,
        double targetValue,
        String category,
        String status,
        double completionPercentage,
        int streak
) {}
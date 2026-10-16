package com.goaltracker.goaltracker.dto;

public record GoalRequestDto (
    String name,
    String type,
    String goalUnit,
    double targetValue,
    String category
){}
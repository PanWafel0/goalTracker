package com.goaltracker.goaltracker.dto;

public record EntryResponseDto(
        int id,
        double value,
        String date,
        boolean done
) {}
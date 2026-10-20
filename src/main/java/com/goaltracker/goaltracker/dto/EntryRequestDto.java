package com.goaltracker.goaltracker.dto;

public record EntryRequestDto(
        double value,
        String date,
        boolean done
) {}
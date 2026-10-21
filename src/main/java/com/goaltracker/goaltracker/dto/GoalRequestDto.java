package com.goaltracker.goaltracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GoalRequestDto(
        @Schema(description = "Nazwa celu", example = "bieganie")
        String name,

        @Schema(description = "Typ celu: NUMERIC lub HABIT", example = "NUMERIC")
        String type,

        @Schema(description = "Jednostka wartości docelowej", example = "km")
        String goalUnit,

        @Schema(description = "Wartość docelowa", example = "10")
        double targetValue,

        @Schema(description = "Kategoria: SPORT, EDUCATION, HEALTH, HOBBY, OTHER", example = "SPORT")
        String category
) {}
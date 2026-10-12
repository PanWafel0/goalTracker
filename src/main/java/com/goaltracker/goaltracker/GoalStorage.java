package com.goaltracker.goaltracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GoalStorage {
    private final String fileName = "goals.json";
    private final ObjectMapper objectMapper;

    public GoalStorage() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        );
    }

    public void saveGoals(List<Goal> goals) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), goals);
        } catch (Exception e) {
            throw new RuntimeException("Nie udało się zapisać pliku " + fileName, e);
        }
    }

    public List<Goal> loadGoals() {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Goal>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Nie udało się wczytać pliku " + fileName, e);
        }
    }
}
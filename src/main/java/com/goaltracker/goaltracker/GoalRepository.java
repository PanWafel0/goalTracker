package com.goaltracker.goaltracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {

    List<Goal> findByIsDeletedFalse();
    List<Goal> findByIsDeletedTrue();
    List<Goal> findByCategoryAndIsDeletedFalse(Category category);
}
package com.goaltracker.goaltracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressEntryRepository extends JpaRepository<ProgressEntry, Integer> {

}
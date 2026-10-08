package com.goaltracker.goaltracker;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GoaltrackerApplication {

		public static void main(String[] args) {
			GoalService service = new GoalService();

			Goal learning = new Goal("Nauka Javy", GoalType.NUMERIC, 500, Category.EDUCATION);
			Goal running = new Goal("Bieganie", GoalType.HABIT, 0, Category.SPORT);
			Goal reading = new Goal("Czytanie", GoalType.HABIT, 0, Category.OTHER);

			service.addGoal(learning);
			service.addGoal(running);
			service.addGoal(reading);

			ProgressEntry firstEntry = new ProgressEntry(250, LocalDate.now());
			ProgressEntry secondEntry = new ProgressEntry(150, LocalDate.now());

			service.addEntryToGoal(learning.getId(), firstEntry);
			service.addEntryToGoal(learning.getId(), secondEntry);

			service.showList();

	}
}





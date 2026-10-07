package com.goaltracker.goaltracker;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class GoaltrackerApplication {



	public static void main(String[] args) {
		GoalService service = new GoalService();

		service.addGoal(
				new Goal("Bieganie", GoalType.NUMERIC, 500, Category.SPORT)
		);

		Goal goal = service.findGoalById(1);

		double startPercentage = goal.getCompletionPercentage();

		goal.addEntry(new ProgressEntry(250, LocalDate.now()));
		double afterFirstEntry = goal.getCompletionPercentage();
		System.out.println(goal.getStatus());
		goal.addEntry(new ProgressEntry(270, LocalDate.now()));
		double afterSecondEntry = goal.getCompletionPercentage();
		goal.addEntry(new ProgressEntry(270, LocalDate.now()));
		double afterThirdEntry = goal.getCompletionPercentage();
		System.out.println("""
                Cel: %s
                Wartość docelowa: %.0f
                Procent na początku: %.1f%%
                Po wpisie 250: %.1f%%
                Po wpisie 270: %.1f%%
                """.formatted(
				goal.getName(),
				goal.getTargetValue(),
				startPercentage,
				afterFirstEntry,
				afterSecondEntry
		));
		System.out.println(goal.getStatus());
	}
	}





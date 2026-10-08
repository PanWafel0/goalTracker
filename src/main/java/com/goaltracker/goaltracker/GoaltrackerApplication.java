package com.goaltracker.goaltracker;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoaltrackerApplication {


		public static void main(String[] args) {
			GoalService service = new GoalService();
			ConsoleMenu menu = new ConsoleMenu(service);

			menu.run();

	}
}





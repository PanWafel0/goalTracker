package com.goaltracker.goaltracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoaltrackerApplication implements CommandLineRunner {

	private final ConsoleMenu consoleMenu;

	public GoaltrackerApplication(ConsoleMenu consoleMenu) {
		this.consoleMenu = consoleMenu;
	}

	public static void main(String[] args) {
		SpringApplication.run(GoaltrackerApplication.class, args);
	}

	@Override
	public void run(String... args){
		consoleMenu.run();
	}
}
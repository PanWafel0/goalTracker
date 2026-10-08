package com.goaltracker.goaltracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleMenu {
    private GoalService service;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(GoalService service) {
        this.service = service;
    }

    public void showGoals() {
        service.showList();
    }

    private int choseOptions(){
        showGoals();
        System.out.println("1: Dodaj cel");
        System.out.println("2: Wybierz cel z listy");
        System.out.println("3: Pokaż kosz");
        System.out.println("0: Zakończ program");
        return scanner.nextInt();
    }
    private void addGoal(){
        System.out.println("Wprowadź nazwę celu: ");
        String name = scanner.next();
        System.out.println("wprowadź typ celu(NUMERIC/HABIT):");
        GoalType type = GoalType.valueOf(scanner.next());
        System.out.println("Wprowadź wartośc docelową: ");
        double targetValue =  scanner.nextDouble();
        System.out.println("wprowadź jednostkę wartości docelowej celu");
        String goalUnit = scanner.next();
        System.out.println("Wprowadź kategorię: ");
        Category category = Category.valueOf(scanner.next());
        Goal goal = new Goal(name, type, goalUnit, targetValue, category);
        service.addGoal(goal);

    }
    private void chooseGoal(){
        System.out.println("Podaj id celu");
        int id = scanner.nextInt();
        manageGoal(id);
    }
    private void manageGoal(int id){
        Goal goal = service.findGoalById(id);

        if(goal == null || goal.isDeleted()){
            System.out.println("nie ma takiego id");
            return;
        }
        int option = choseGoalOptions(goal);
        switch (option){
            case 1:
                addEntry(goal.getId());
                break;
            case 2:
                deleteEntry(goal.getId());
                break;
            case 3:
                deleteGoalToTrash(goal.getId());
                break;
            case 4:
                if(!goal.isFinished()){
                    changeTargetValue();
                    break;
                }
                else{
                    System.out.println("Nie zmienić wartości docelowej celu, który nie jest jeszcze ukończony");
                    break;
                }
            default:
                break;
        }
    }
    private void changeTargetValue(){

    }
    private void addEntry(int goalId){
        System.out.println("Podaj wartość wpisu: ");
        double value = scanner.nextDouble();
        System.out.println("Podaj datę");
        scanner.nextLine();
        String dateInput = scanner.nextLine();
        try {
            LocalDate date = LocalDate.parse(dateInput);
            ProgressEntry entry = new ProgressEntry(value, date);
            if(service.addEntryToGoal(goalId, entry)){
                System.out.println("Utworzono wpis postępu");
            }

        } catch (DateTimeParseException e) {
            System.out.println("Nieprawidłowy format daty! Użyj formatu RRRR-MM-DD.");
        }
    }
    private void deleteEntry(int goalId){
        System.out.println("Podaj id wpisu który chcesz usunąć: ");
        int id = scanner.nextInt();
        if(service.deleteEntryFromGoal(id, goalId)){
            System.out.println("Pomyślnie usunięto wpis postepu");
        }
        else{
            System.out.println("Wystąpił błąd");
        }
    }
    private void deleteGoalToTrash(int goalId){
        if(service.moveGoalToTrashById(goalId)){
            System.out.println("Pomyślnie przeniesiono do kosza cel");
        }
        else{
            System.out.println("Wystąpił błąd podczas usuwania celu");
        }
    }
    private int choseGoalOptions(Goal goal){
        System.out.println("1: Dodaj wpis");
        System.out.println("2: Usuń wpis");
        System.out.println("3: usuń cel");
        if(!goal.getStatus().equals("Zakończony")) {
            System.out.println("4: Zmień wartośc docelową celu");
        }
        System.out.println("0: Zakończ program");
        return scanner.nextInt();
    }
    public void run() {
        boolean running = true;

        while (running) {
            int choice = choseOptions();

            switch (choice) {
                case 1:
                    addGoal();
                    break;
                case 2:
                    chooseGoal();
                    break;
                case 3:
                    service.showGoalsInTrash();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Błędna opcja. Spróbuj ponownie.");
                    break;
            }
        }
    }
}

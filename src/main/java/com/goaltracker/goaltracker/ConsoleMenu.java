package com.goaltracker.goaltracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleMenu {
    private final GoalService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(GoalService service) {
        this.service = service;
    }

    public void run(){
        commonRun();
    }

    private void commonRun() {
        boolean running = true;

        while (running) {
            int choice = choseCommonOptions();

            switch (choice) {
                case 1:
                    addGoal();
                    break;
                case 2:
                    chooseGoal(false);
                    break;
                case 3:
                    openTrash();
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

    private void trashRun() {
        boolean running = true;

        while (running) {
            int choice = chooseTrashOptions();

            switch (choice) {
                case 1:
                    chooseGoal(true);
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

    //wypisywanie opcji do wybrania
    private int choseCommonOptions(){
        showGoals();
        System.out.println("1: Dodaj cel");
        System.out.println("2: Wybierz cel z listy");
        System.out.println("3: Pokaż kosz");
        System.out.println("0: Zakończ program");
        return scanner.nextInt();
    }
    private int choseGoalOptions(Goal goal){
        System.out.println("1: Dodaj wpis");
        System.out.println("2: Usuń wpis");
        System.out.println("3: usuń cel");
        if(goal.getStatus().equals("Zakończony")) {
            System.out.println("4: Zmień wartośc docelową celu");
        }
        System.out.println("0: Wróć");
        return scanner.nextInt();
    }
    private int chooseTrashGoalOptions(){
        System.out.println("1: przywróć cel");
        System.out.println("2: usuń cel permanentnie");
        System.out.println("0: wróć");
        return scanner.nextInt();
    }
    private int chooseTrashOptions(){
        showTrashGoals();
        System.out.println("1: Wybierz cel z kosza");
        System.out.println("0: wróć");
        return scanner.nextInt();
    }

    private void chooseGoal(boolean isTrash){
        System.out.println("Podaj id celu");
        int id = scanner.nextInt();
        if (!isTrash) {
            manageGoal(id);
        } else {
            manageTrashGoal(id);
        }
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
                if(goal.isFinished()){
                    changeTargetValue(goal.getId());
                }
                else{
                    System.out.println("Nie można zmienić wartości docelowej celu, który nie jest jeszcze ukończony");
                }
                break;
            default:
                break;
        }
    }
    private void manageTrashGoal(int id){
        Goal goal = service.findGoalById(id);

        if(goal == null || !goal.isDeleted()){
            System.out.println("nie ma takiego id");
            return;
        }
        int option = chooseTrashGoalOptions();
        switch (option){
            case 1:
                restoreGoalFromTrash(goal.getId());
                break;
            case 2:
                deleteGoalPermanently(goal.getId());
                break;
            default:
                break;
        }
    }

    private void showGoals() {
        service.showList();
    }
    private void showTrashGoals(){
        service.showGoalsInTrash();
    }

    //dodawanie, wybieranie i usuwanie celów
    private void addGoal(){
        System.out.println("Wprowadź nazwę celu: ");
        String name = scanner.next();
        System.out.println("wprowadź typ celu(NUMERIC/HABIT):");
        GoalType type = GoalType.valueOf(scanner.next());
        double targetValue = 0;
        String goalUnit = "";
        if(type==GoalType.NUMERIC) {
            System.out.println("Wprowadź wartośc docelową: ");
            targetValue =  scanner.nextDouble();
            System.out.println("wprowadź jednostkę wartości docelowej celu");
            goalUnit = scanner.next();
        }

        System.out.println("Wprowadź kategorię: ");
        Category category = Category.valueOf(scanner.next());
        Goal goal = new Goal(name, type, goalUnit, targetValue, category);
        service.addGoal(goal);

    }

    private void changeTargetValue(int goalId){
        System.out.println("Podaj nową wartość docelową(np.1,1): ");
        double value = scanner.nextDouble();
        if(service.changeGoalTargetValue(goalId, value)) {
            System.out.println("Zmieniono wartość docelową celu");
        }
        else{
            System.out.println("Nie udało sie zmienic wartości docelowej celu");
        }
    }

    //dodawanie i usuwanie wpisów
    private void addEntry(int goalId){
        Goal goal = service.findGoalById(goalId);
        if (goal == null){
            return;
        }

        double value = 0;
        boolean done = false;
        LocalDate date;

        if(goal.getType() == GoalType.NUMERIC) {
            System.out.println("Podaj wartość wpisu: ");
            value = scanner.nextDouble();
        }
        else{
            System.out.println("Wykonano? (tak/nie)");
            String answer = scanner.next();

            if(answer.equalsIgnoreCase("tak")){
                done = true;
            } else if (answer.equalsIgnoreCase("nie")) {
                done= false;
            } else{
                System.out.println("Odpowiedź musi być tak lub nie");
                return;
            }
        }

        System.out.println("Podaj datę");
        scanner.nextLine();
        String dateInput = scanner.nextLine();

        try {
            date = LocalDate.parse(dateInput);

            ProgressEntry entry;
            if(goal.getType() == GoalType.NUMERIC){
                entry = new ProgressEntry(value, date);
            }
            else{
                entry = new ProgressEntry(done, date);
            }

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

    private void openTrash(){
        trashRun();
    }

    private void restoreGoalFromTrash(int trashGoalId){
        if(service.restoreGoalFromTrashById(trashGoalId)){
            System.out.println("Pomyślnie przywrócono cel z kosza");
        }
        else{
            System.out.println("Wystąpił błąd podczas usuwania celu z kosza");
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

    private void deleteGoalPermanently(int goalId){
        if(service.deleteGoalPermanentlyById(goalId)){
            System.out.println("Pomyślnie usunięto cel");
        }
        else{
            System.out.println("Wystąpił błąd podczas usuwania celu");
        }
    }
}
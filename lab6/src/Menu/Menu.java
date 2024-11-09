package Menu;

import Commands.*;
import Model.Chef;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu implements Command {
    private Map<Integer, Command> commands = new HashMap<>();
    Chef chef = new Chef();

    public void setCommand(int option, Command command) {
        commands.put(option, command);
    }

    public Menu() {
        initializeMenuActions();
    }

    public void selectOption(int option) {
        Command command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Невідома команда");
        }
    }

    protected void initializeMenuActions() {
        setCommand(1, new CreateSaladCommand(chef));
        setCommand(2, new CalculateCaloriesCommand(chef));
        setCommand(3, new SortVegetablesCommand(chef));
        setCommand(4, new FindIngredientsByCaloriesCommand(chef));
        setCommand(5, new AddIngredientCommand(chef));
        setCommand(6, new DeleteIngredientCommand(chef));
        setCommand(7, new SortIngredientsByIdCommand(chef));
    }

    public void showMenu() {
        System.out.println("\nМеню:");
        for (var entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }
        System.out.println("0. Вийти");
    }

    @Override
    public void execute() {
        showMenu();
        System.out.print("Оберіть опцію: ");

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            option = scanner.nextInt();
            if (option == 0) break;
            selectOption(option);
        } while (option != 0);
    }

    @Override
    public String toString() {
        return "Показати команди";
    }
}

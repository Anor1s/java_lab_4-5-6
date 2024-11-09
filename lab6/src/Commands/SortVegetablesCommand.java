package Commands;

import Model.Chef;
import Model.SortParameter;
import java.util.Scanner;

public class SortVegetablesCommand implements Command {
    private Chef chef;

    public SortVegetablesCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nОберіть параметр для сортування:");
        System.out.println("1. Калорійність");
        System.out.println("2. Вага");

        int choice = scanner.nextInt();
        SortParameter parameter;

        switch (choice) {
            case 1:
                parameter = SortParameter.CALORIES;
                break;
            case 2:
                parameter = SortParameter.WEIGHT;
                break;
            default:
                System.out.println("Невірний вибір!");
                return;
        }

        chef.sortVegetables(parameter);
    }

    @Override
    public String toString() {
        return "Відсортувати інгредиєнти по калоріям";
    }
}

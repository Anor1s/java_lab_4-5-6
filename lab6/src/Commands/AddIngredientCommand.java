package Commands;

import Model.Chef;
import java.util.Scanner;

public class AddIngredientCommand implements Command {
    private Chef chef;

    public AddIngredientCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.addIngredientToFile();
    }

    @Override
    public String toString() {
        return "Додати інгредиєнт у файл";
    }
}

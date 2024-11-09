package Commands;

import Model.Chef;
import java.util.Scanner;

public class DeleteIngredientCommand implements Command {
    private Chef chef;

    public DeleteIngredientCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.deleteIngredientFromFile();
    }

    @Override
    public String toString() {
        return "Видалити інгредиєнт з файлу";
    }
}

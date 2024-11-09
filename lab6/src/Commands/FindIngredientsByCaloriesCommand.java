package Commands;

import Model.Chef;

public class FindIngredientsByCaloriesCommand implements Command {
    private Chef chef;

    public FindIngredientsByCaloriesCommand(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.findIngredientsByCalories();
    }

    @Override
    public String toString() {
        return "Знайти інгредиєнти по калоріям";
    }
}
